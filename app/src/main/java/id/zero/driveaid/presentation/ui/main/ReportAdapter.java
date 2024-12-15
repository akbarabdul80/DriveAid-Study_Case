package id.zero.driveaid.presentation.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.zero.driveaid.databinding.ItemReportBinding;
import id.zero.driveaid.domain.model.DataReportVehicle;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportHolder> {
    private List<DataReportVehicle> dataReport;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReportBinding binding = ItemReportBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReportHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, int position) {
        DataReportVehicle dataReport = this.dataReport.get(position);
        holder.binding.tvReportID.setText(dataReport.getReportId());
        holder.binding.tvReportDate.setText(dataReport.getCreatedAt());
        holder.binding.tvReportStatus.setText(dataReport.getReportStatus());
        holder.binding.tvVehicleName.setText(dataReport.getVehicleName());
        holder.binding.tvVehiclePlate.setText(dataReport.getVehicleLicenseNumber());
        holder.binding.tvUserName.setText(dataReport.getCreatedBy());
        holder.binding.tvReportNote.setText(dataReport.getNote());

        Glide.with(holder.itemView.getContext())
                .load(dataReport.getPhoto())
                .into(holder.binding.ivPhoto);

    }

    @Override
    public int getItemCount() { return dataReport != null ? dataReport.size() : 0; }

    public void setDataReport(List<DataReportVehicle> dataReport) {
        this.dataReport = dataReport;
        notifyDataSetChanged();
    }

    class ReportHolder extends RecyclerView.ViewHolder {
        private final ItemReportBinding binding;

        public ReportHolder(@NonNull ItemReportBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(dataReport.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DataReportVehicle report);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}