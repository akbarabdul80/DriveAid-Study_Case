package id.zero.driveaid.presentation.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.zero.driveaid.R;
import id.zero.driveaid.data.repository.VehicleRepository;
import id.zero.driveaid.databinding.ActivityMainBinding;
import id.zero.driveaid.domain.usecase.GetVehiclesUseCase;
import id.zero.driveaid.presentation.ui.add.CreateReportFragment;
import id.zero.driveaid.presentation.viewmodel.VehicleViewModel;
import id.zero.driveaid.presentation.viewmodel.VehicleViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private VehicleViewModel vehicleViewModel;
    private ReportAdapter adapterReport;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VehicleRepository vehicleRepository = new VehicleRepository();
        GetVehiclesUseCase getVehiclesUseCase = new GetVehiclesUseCase(vehicleRepository);
        vehicleViewModel = new ViewModelProvider(this, new VehicleViewModelFactory(getVehiclesUseCase)).get(VehicleViewModel.class);

        binding.rvReports.setLayoutManager(new LinearLayoutManager(this));
        adapterReport = new ReportAdapter();
        binding.rvReports.setAdapter(adapterReport);

        vehicleViewModel.getReportVehicle();

        binding.btnAddReport.setOnClickListener(v -> {
            CreateReportFragment createReportFragment = new CreateReportFragment();
            createReportFragment.show(getSupportFragmentManager(), "Create Report");
        });

        initObserver();
    }

    private void initObserver() {
        vehicleViewModel.getStateReportVehicle().observe(this, state -> {
            switch (state.getStatus()) {
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.rvReports.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.rvReports.setVisibility(View.VISIBLE);
                    adapterReport.setDataReport(state.getReport());
                    break;
                case ERROR:
                    Toast.makeText(this, state.getErrorMesssage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}