package id.zero.driveaid.presentation.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.zero.driveaid.domain.model.DataVehicle;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<DataVehicle> vehicles;

    public void setVehicles(List<DataVehicle> vehicles) {
        this.vehicles = vehicles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        DataVehicle vehicle = vehicles.get(position);
        holder.text1.setText(vehicle.getType());
        holder.text2.setText(vehicle.getLicenseNumber());
    }

    @Override
    public int getItemCount() {
        return vehicles != null ? vehicles.size() : 0;
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
