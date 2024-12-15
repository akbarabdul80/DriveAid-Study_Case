package id.zero.driveaid.presentation.viewmodel;

import static id.zero.driveaid.utils.ConsVal.USER_ID;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.List;

import id.zero.driveaid.domain.model.DataReportVehicle;
import id.zero.driveaid.domain.model.DataStatus;
import id.zero.driveaid.domain.model.DataVehicle;
import id.zero.driveaid.domain.usecase.GetVehiclesUseCase;
import id.zero.driveaid.presentation.viewmodel.state.StateCreateReportVehicle;
import id.zero.driveaid.presentation.viewmodel.state.StateGetReportVehicle;
import id.zero.driveaid.presentation.viewmodel.state.StateGetVehicle;
import id.zero.driveaid.presentation.viewmodel.state.Status;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class VehicleViewModel extends ViewModel {
    private final GetVehiclesUseCase getVehiclesUseCase;
    private final MutableLiveData<StateGetVehicle> stateGetVehicle = new MutableLiveData<>();
    private final MutableLiveData<StateGetReportVehicle> stateGetReportVehicle = new MutableLiveData<>();
    private final MutableLiveData<StateCreateReportVehicle> stateCreateReportVehicle = new MutableLiveData<>();


    public VehicleViewModel(GetVehiclesUseCase getVehiclesUseCase) {
        this.getVehiclesUseCase = getVehiclesUseCase;
    }

    public LiveData<StateGetVehicle> getStateVehicle() {
        return stateGetVehicle;
    }

    public LiveData<StateGetReportVehicle> getStateReportVehicle() {
        return stateGetReportVehicle;
    }

    public LiveData<StateCreateReportVehicle> getStateCreateReportVehicle() {
        return stateCreateReportVehicle;
    }

    public void getVehicle() {
        getVehiclesUseCase.getVehicle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<DataVehicle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        stateGetVehicle.setValue(new StateGetVehicle(Status.LOADING, null, null));
                    }

                    @Override
                    public void onSuccess(List<DataVehicle> vehicleList) {
                        stateGetVehicle.setValue(new StateGetVehicle(Status.SUCCESS, vehicleList, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        stateGetVehicle.setValue(new StateGetVehicle(Status.ERROR, null, e.getMessage()));
                    }
                });
    }

    public void getReportVehicle() {
        getVehiclesUseCase.getReportVehicle("", USER_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<DataReportVehicle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        stateGetReportVehicle.setValue(new StateGetReportVehicle(Status.LOADING, null, null));
                    }

                    @Override
                    public void onSuccess(List<DataReportVehicle> dataReport) {
                        stateGetReportVehicle.setValue(new StateGetReportVehicle(Status.SUCCESS, dataReport, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        stateGetReportVehicle.setValue(new StateGetReportVehicle(Status.ERROR, null, e.getMessage()));
                    }
                });

    }

    public void getReportVehicle(
            String licenseNumber
    ) {
        getVehiclesUseCase.getReportVehicle(licenseNumber, USER_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<DataReportVehicle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        stateGetReportVehicle.setValue(new StateGetReportVehicle(Status.LOADING, null, null));
                    }

                    @Override
                    public void onSuccess(List<DataReportVehicle> dataReport) {
                        stateGetReportVehicle.setValue(new StateGetReportVehicle(Status.SUCCESS, dataReport, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        stateGetReportVehicle.setValue(new StateGetReportVehicle(Status.ERROR, null, e.getMessage()));
                    }
                });
    }

    public void addReportVehicle(
            String vehicleId,
            String note,
            File photo
    ) {
        getVehiclesUseCase.addReportVehicle(
                        vehicleId,
                        note,
                        photo,
                        USER_ID
                ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<DataStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        stateCreateReportVehicle.setValue(new StateCreateReportVehicle(Status.LOADING, null, null));
                    }

                    @Override
                    public void onSuccess(DataStatus dataReport) {
                        stateCreateReportVehicle.setValue(new StateCreateReportVehicle(Status.SUCCESS, dataReport, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        stateCreateReportVehicle.setValue(new StateCreateReportVehicle(Status.ERROR, null, e.getMessage()));
                    }
                });
    }


}
