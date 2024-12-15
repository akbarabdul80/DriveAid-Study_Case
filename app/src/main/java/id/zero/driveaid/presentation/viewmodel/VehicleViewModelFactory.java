package id.zero.driveaid.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.zero.driveaid.domain.usecase.GetVehiclesUseCase;

public class VehicleViewModelFactory implements ViewModelProvider.Factory {

    private final GetVehiclesUseCase getVehiclesUseCase;

    public VehicleViewModelFactory(GetVehiclesUseCase getVehiclesUseCase) {
        this.getVehiclesUseCase = getVehiclesUseCase;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new VehicleViewModel(getVehiclesUseCase);
    }
}
