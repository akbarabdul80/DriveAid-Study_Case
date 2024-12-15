package id.zero.driveaid.presentation.ui.add;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import id.zero.driveaid.R;
import id.zero.driveaid.data.repository.VehicleRepository;
import id.zero.driveaid.databinding.BottomCreateReportBinding;
import id.zero.driveaid.domain.model.DataVehicle;
import id.zero.driveaid.domain.usecase.GetVehiclesUseCase;
import id.zero.driveaid.presentation.viewmodel.VehicleViewModel;
import id.zero.driveaid.presentation.viewmodel.VehicleViewModelFactory;
import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class CreateReportFragment extends BottomSheetDialogFragment {
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;

    private File selectedImageFile;
    private VehicleViewModel vehicleViewModel;

    BottomCreateReportBinding binding;

    EasyImage easyImage;
    List<DataVehicle> vehicles;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = BottomCreateReportBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VehicleRepository vehicleRepository = new VehicleRepository();
        GetVehiclesUseCase getVehiclesUseCase = new GetVehiclesUseCase(vehicleRepository);
        vehicleViewModel = new ViewModelProvider(this, new VehicleViewModelFactory(getVehiclesUseCase)).get(VehicleViewModel.class);
        initObserver();
        vehicleViewModel.getVehicle();

        easyImage = new EasyImage.Builder(requireContext())
                .setCopyImagesToPublicGalleryFolder(false)
                .setFolderName(String.valueOf(R.string.app_name))
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .allowMultiple(false)
                .build();


        binding.llSelectImage.setOnClickListener(v -> {
            easyImage.openChooser(CreateReportFragment.this);
        });

        binding.btnAddReport.setOnClickListener(v -> {
            if (selectedImageFile == null) {
                Toast.makeText(requireContext(), "Please select image", Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.etNote.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter note", Toast.LENGTH_SHORT).show();
                return;
            }

            DataVehicle vehicle = vehicles.get(binding.spnVehicle.getSelectedItemPosition());

            vehicleViewModel.addReportVehicle(
                    vehicle.getVehicleId(),
                    binding.etNote.getText().toString(),
                    selectedImageFile
            );
        });


        Handler handler = new Handler();
        Runnable updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateDateTime();
                handler.postDelayed(this, 60000);
            }
        };

        handler.post(updateTimeRunnable);
    }

    private void updateDateTime() {
        Calendar calendar = Calendar.getInstance();

        // Format date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM HH:mm", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());

        // Update the TextView with the formatted date and time
        binding.tvDatetime.setText(formattedDate);
    }


    private void initObserver() {
        vehicleViewModel.getStateVehicle().observe(this, state -> {
            switch (state.getStatus()) {
                case LOADING:
                    binding.pbSpnLoading.setVisibility(View.VISIBLE);
                    binding.spnVehicle.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    binding.pbSpnLoading.setVisibility(View.GONE);
                    binding.spnVehicle.setVisibility(View.VISIBLE);

                    vehicles = state.getVehicles();

                    List<String> vehicleDisplayList = state.getVehicles().stream()
                            .map(vehicle -> vehicle.getType()  + " - " + vehicle.getLicenseNumber())
                            .collect(Collectors.toList());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            vehicleDisplayList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spnVehicle.setAdapter(adapter);
                    break;
                case ERROR:
                    if (state.getErrorMesssage() != null) {
                        Toast.makeText(requireContext(), state.getErrorMesssage(), Toast.LENGTH_SHORT).show();
                    } else  {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });

        vehicleViewModel.getStateCreateReportVehicle().observe(this, state -> {
            switch (state.getStatus()) {
                case LOADING:
                    binding.btnAddReport.setEnabled(false);
                    binding.btnAddReport.setVisibility(View.GONE);
                    binding.pbLoading.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    binding.btnAddReport.setEnabled(true);
                    binding.btnAddReport.setVisibility(View.VISIBLE);
                    binding.pbLoading.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Report added", Toast.LENGTH_SHORT).show();
                    dismiss();
                    break;
                case ERROR:
                    binding.btnAddReport.setEnabled(true);
                    binding.btnAddReport.setVisibility(View.VISIBLE);
                    binding.pbLoading.setVisibility(View.GONE);
                    if (state.getErrorMesssage() != null) {
                        Toast.makeText(requireContext(), state.getErrorMesssage(), Toast.LENGTH_SHORT).show();
                    } else  {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, requireActivity(), new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                onPhotosReturned(imageFiles);
            }

            @Override
            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
                //Some error handling
                error.printStackTrace();
            }

            @Override
            public void onCanceled(@NonNull MediaSource source) {
                //Not necessary to remove any files manually anymore
            }
        });
    }

    private void onPhotosReturned(MediaFile[] imageFiles) {
        if (imageFiles != null && imageFiles.length > 0) {
            selectedImageFile = imageFiles[0].getFile();
            binding.llSelectImage.setVisibility(View.GONE);
            binding.ivPhoto.setVisibility(View.VISIBLE);
            binding.ivPhoto.setImageURI(Uri.fromFile(selectedImageFile));
        }
    }

}
