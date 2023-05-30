package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDiarioBindingImpl extends FragmentDiarioBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.contenitore_calorie, 17);
        sViewsWithIds.put(R.id.linearLayout12, 18);
        sViewsWithIds.put(R.id.textView18, 19);
        sViewsWithIds.put(R.id.progress_calorie, 20);
        sViewsWithIds.put(R.id.Testo_statico, 21);
        sViewsWithIds.put(R.id.textView22, 22);
        sViewsWithIds.put(R.id.linearLayout13, 23);
        sViewsWithIds.put(R.id.textView15, 24);
        sViewsWithIds.put(R.id.text, 25);
        sViewsWithIds.put(R.id.textView, 26);
        sViewsWithIds.put(R.id.textView24, 27);
        sViewsWithIds.put(R.id.pgBar_proteine, 28);
        sViewsWithIds.put(R.id.textVi, 29);
        sViewsWithIds.put(R.id.textV, 30);
        sViewsWithIds.put(R.id.textView26, 31);
        sViewsWithIds.put(R.id.pgBar_grassi, 32);
        sViewsWithIds.put(R.id.descrizione_dieta, 33);
        sViewsWithIds.put(R.id.textView21, 34);
        sViewsWithIds.put(R.id.contenitore_acqua, 35);
        sViewsWithIds.put(R.id.textView23, 36);
        sViewsWithIds.put(R.id.imageViewGoldMedal, 37);
        sViewsWithIds.put(R.id.linearSeparatore, 38);
        sViewsWithIds.put(R.id.imageView72, 39);
        sViewsWithIds.put(R.id.linearLayoutGlass, 40);
        sViewsWithIds.put(R.id.glass_1, 41);
        sViewsWithIds.put(R.id.glass_2, 42);
        sViewsWithIds.put(R.id.glass_3, 43);
        sViewsWithIds.put(R.id.glass_4, 44);
        sViewsWithIds.put(R.id.glass_5, 45);
        sViewsWithIds.put(R.id.glass_6, 46);
        sViewsWithIds.put(R.id.glass_7, 47);
        sViewsWithIds.put(R.id.glass_8, 48);
        sViewsWithIds.put(R.id.colazione, 49);
        sViewsWithIds.put(R.id.imageView73, 50);
        sViewsWithIds.put(R.id.textView32, 51);
        sViewsWithIds.put(R.id.textView332, 52);
        sViewsWithIds.put(R.id.pranzo, 53);
        sViewsWithIds.put(R.id.imageView74, 54);
        sViewsWithIds.put(R.id.textView33, 55);
        sViewsWithIds.put(R.id.textView309, 56);
        sViewsWithIds.put(R.id.cena, 57);
        sViewsWithIds.put(R.id.imageView76, 58);
        sViewsWithIds.put(R.id.textView36, 59);
        sViewsWithIds.put(R.id.textView12, 60);
        sViewsWithIds.put(R.id.spuntino, 61);
        sViewsWithIds.put(R.id.imageView79, 62);
        sViewsWithIds.put(R.id.textView38, 63);
        sViewsWithIds.put(R.id.textView632, 64);
        sViewsWithIds.put(R.id.esercizio, 65);
        sViewsWithIds.put(R.id.imageView80, 66);
        sViewsWithIds.put(R.id.textView40, 67);
        sViewsWithIds.put(R.id.textView352, 68);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDiarioBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 69, sIncludes, sViewsWithIds));
    }
    private FragmentDiarioBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.LinearLayout) bindings[57]
            , (android.widget.LinearLayout) bindings[49]
            , (android.widget.LinearLayout) bindings[35]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.TextView) bindings[33]
            , (android.widget.LinearLayout) bindings[65]
            , (android.widget.ImageView) bindings[41]
            , (android.widget.ImageView) bindings[42]
            , (android.widget.ImageView) bindings[43]
            , (android.widget.ImageView) bindings[44]
            , (android.widget.ImageView) bindings[45]
            , (android.widget.ImageView) bindings[46]
            , (android.widget.ImageView) bindings[47]
            , (android.widget.ImageView) bindings[48]
            , (android.widget.ImageView) bindings[39]
            , (android.widget.ImageView) bindings[50]
            , (android.widget.ImageView) bindings[54]
            , (android.widget.ImageView) bindings[58]
            , (android.widget.ImageView) bindings[62]
            , (android.widget.ImageView) bindings[66]
            , (android.widget.ImageView) bindings[37]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[15]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[40]
            , (android.widget.LinearLayout) bindings[38]
            , (android.widget.ProgressBar) bindings[4]
            , (android.widget.ProgressBar) bindings[32]
            , (android.widget.ProgressBar) bindings[28]
            , (android.widget.LinearLayout) bindings[53]
            , (com.mikhaellopez.circularprogressbar.CircularProgressBar) bindings[20]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[7]
            , (android.widget.LinearLayout) bindings[61]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[60]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[56]
            , (android.widget.TextView) bindings[51]
            , (android.widget.TextView) bindings[55]
            , (android.widget.TextView) bindings[52]
            , (android.widget.TextView) bindings[68]
            , (android.widget.TextView) bindings[59]
            , (android.widget.TextView) bindings[63]
            , (android.widget.TextView) bindings[67]
            , (android.widget.TextView) bindings[64]
            );
        this.calAssunte.setTag(null);
        this.calBruciate.setTag(null);
        this.calorieRimanenti.setTag(null);
        this.kcalCena.setTag(null);
        this.kcalColazione.setTag(null);
        this.kcalEsercizio.setTag(null);
        this.kcalPranzo.setTag(null);
        this.kcalSpuntino.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pgBarCarboidrati.setTag(null);
        this.qntCarboMAX.setTag(null);
        this.qntCarboidrati.setTag(null);
        this.qntGrassi.setTag(null);
        this.qntGrsMAX.setTag(null);
        this.qntProMAX.setTag(null);
        this.qntProteine.setTag(null);
        this.textView25.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x100L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.example.letmebeyourchef.diario.DiarioViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.letmebeyourchef.diario.DiarioViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelAssunte((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelDiario((androidx.lifecycle.LiveData<com.example.letmebeyourchef.model.Diario>) object, fieldId);
            case 2 :
                return onChangeViewModelProteineMax((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 3 :
                return onChangeViewModelResult((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelCarboidratiMax((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 5 :
                return onChangeViewModelAcqua((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelGrassiMax((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelAssunte(androidx.lifecycle.LiveData<java.lang.String> ViewModelAssunte, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDiario(androidx.lifecycle.LiveData<com.example.letmebeyourchef.model.Diario> ViewModelDiario, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelProteineMax(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelProteineMax, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelResult(androidx.lifecycle.LiveData<java.lang.String> ViewModelResult, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelCarboidratiMax(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelCarboidratiMax, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAcqua(androidx.lifecycle.LiveData<java.lang.String> ViewModelAcqua, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGrassiMax(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelGrassiMax, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        androidx.lifecycle.LiveData<java.lang.String> viewModelAssunte = null;
        androidx.lifecycle.LiveData<com.example.letmebeyourchef.model.Diario> viewModelDiario = null;
        java.lang.Integer integerValueOfViewModelDiarioChiloCalorieSpuntino = null;
        java.lang.Integer integerValueOfViewModelDiarioProteineTot = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelProteineMax = null;
        int viewModelDiarioChiloCaloriePranzo = 0;
        androidx.lifecycle.LiveData<java.lang.String> viewModelResult = null;
        java.lang.String integerValueOfViewModelDiarioProteineTotToString = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelCarboidratiMax = null;
        int viewModelDiarioChiloCalorieEsercizio = 0;
        java.lang.String viewModelResultGetValue = null;
        int viewModelDiarioProteineTot = 0;
        java.lang.Integer integerValueOfViewModelDiarioChiloCalorieEsercizio = null;
        java.lang.String viewModelAcquaGetValue = null;
        java.lang.Integer integerValueOfViewModelDiarioChiloCaloriePranzo = null;
        int viewModelDiarioChiloCalorieColazione = 0;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelProteineMaxGetValue = 0;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelCarboidratiMaxGetValue = 0;
        int viewModelDiarioCarboidratiTot = 0;
        int viewModelDiarioGrassiTot = 0;
        java.lang.Integer integerValueOfViewModelDiarioChiloCalorieColazione = null;
        java.lang.Integer integerValueOfViewModelDiarioGrassiTot = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAcqua = null;
        java.lang.String integerValueOfViewModelDiarioChiloCalorieSpuntinoToString = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelGrassiMax = null;
        java.lang.String integerValueOfViewModelGrassiMaxToString = null;
        java.lang.String integerValueOfViewModelProteineMaxToString = null;
        java.lang.Integer integerValueOfViewModelCarboidratiMax = null;
        java.lang.Integer integerValueOfViewModelDiarioChiloCalorieCena = null;
        java.lang.String integerValueOfViewModelDiarioChiloCalorieCenaToString = null;
        java.lang.Integer viewModelCarboidratiMaxGetValue = null;
        java.lang.String integerValueOfViewModelCarboidratiMaxToString = null;
        java.lang.String integerValueOfViewModelDiarioChiloCaloriePranzoToString = null;
        int viewModelDiarioChiloCalorieSpuntino = 0;
        java.lang.Integer integerValueOfViewModelDiarioCarboidratiTot = null;
        java.lang.String integerValueOfViewModelDiarioCarboidratiTotToString = null;
        java.lang.Integer integerValueOfViewModelGrassiMax = null;
        java.lang.Integer viewModelGrassiMaxGetValue = null;
        java.lang.String integerValueOfViewModelDiarioChiloCalorieEsercizioToString = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelGrassiMaxGetValue = 0;
        java.lang.String integerValueOfViewModelDiarioGrassiTotToString = null;
        java.lang.String viewModelAssunteGetValue = null;
        java.lang.Integer viewModelProteineMaxGetValue = null;
        int viewModelDiarioChiloCalorieCena = 0;
        com.example.letmebeyourchef.model.Diario viewModelDiarioGetValue = null;
        java.lang.Integer integerValueOfViewModelProteineMax = null;
        com.example.letmebeyourchef.diario.DiarioViewModel viewModel = mViewModel;
        java.lang.String integerValueOfViewModelDiarioChiloCalorieColazioneToString = null;

        if ((dirtyFlags & 0x1ffL) != 0) {


            if ((dirtyFlags & 0x181L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.assunte
                        viewModelAssunte = viewModel.getAssunte();
                    }
                    updateLiveDataRegistration(0, viewModelAssunte);


                    if (viewModelAssunte != null) {
                        // read viewModel.assunte.getValue()
                        viewModelAssunteGetValue = viewModelAssunte.getValue();
                    }
            }
            if ((dirtyFlags & 0x182L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.diario
                        viewModelDiario = viewModel.getDiario();
                    }
                    updateLiveDataRegistration(1, viewModelDiario);


                    if (viewModelDiario != null) {
                        // read viewModel.diario.getValue()
                        viewModelDiarioGetValue = viewModelDiario.getValue();
                    }


                    if (viewModelDiarioGetValue != null) {
                        // read viewModel.diario.getValue().chiloCaloriePranzo
                        viewModelDiarioChiloCaloriePranzo = viewModelDiarioGetValue.getChiloCaloriePranzo();
                        // read viewModel.diario.getValue().chiloCalorieEsercizio
                        viewModelDiarioChiloCalorieEsercizio = viewModelDiarioGetValue.getChiloCalorieEsercizio();
                        // read viewModel.diario.getValue().proteineTot
                        viewModelDiarioProteineTot = viewModelDiarioGetValue.getProteineTot();
                        // read viewModel.diario.getValue().chiloCalorieColazione
                        viewModelDiarioChiloCalorieColazione = viewModelDiarioGetValue.getChiloCalorieColazione();
                        // read viewModel.diario.getValue().carboidratiTot
                        viewModelDiarioCarboidratiTot = viewModelDiarioGetValue.getCarboidratiTot();
                        // read viewModel.diario.getValue().grassiTot
                        viewModelDiarioGrassiTot = viewModelDiarioGetValue.getGrassiTot();
                        // read viewModel.diario.getValue().chiloCalorieSpuntino
                        viewModelDiarioChiloCalorieSpuntino = viewModelDiarioGetValue.getChiloCalorieSpuntino();
                        // read viewModel.diario.getValue().chiloCalorieCena
                        viewModelDiarioChiloCalorieCena = viewModelDiarioGetValue.getChiloCalorieCena();
                    }


                    // read Integer.valueOf(viewModel.diario.getValue().chiloCaloriePranzo)
                    integerValueOfViewModelDiarioChiloCaloriePranzo = java.lang.Integer.valueOf(viewModelDiarioChiloCaloriePranzo);
                    // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieEsercizio)
                    integerValueOfViewModelDiarioChiloCalorieEsercizio = java.lang.Integer.valueOf(viewModelDiarioChiloCalorieEsercizio);
                    // read Integer.valueOf(viewModel.diario.getValue().proteineTot)
                    integerValueOfViewModelDiarioProteineTot = java.lang.Integer.valueOf(viewModelDiarioProteineTot);
                    // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieColazione)
                    integerValueOfViewModelDiarioChiloCalorieColazione = java.lang.Integer.valueOf(viewModelDiarioChiloCalorieColazione);
                    // read Integer.valueOf(viewModel.diario.getValue().carboidratiTot)
                    integerValueOfViewModelDiarioCarboidratiTot = java.lang.Integer.valueOf(viewModelDiarioCarboidratiTot);
                    // read Integer.valueOf(viewModel.diario.getValue().grassiTot)
                    integerValueOfViewModelDiarioGrassiTot = java.lang.Integer.valueOf(viewModelDiarioGrassiTot);
                    // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieSpuntino)
                    integerValueOfViewModelDiarioChiloCalorieSpuntino = java.lang.Integer.valueOf(viewModelDiarioChiloCalorieSpuntino);
                    // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieCena)
                    integerValueOfViewModelDiarioChiloCalorieCena = java.lang.Integer.valueOf(viewModelDiarioChiloCalorieCena);


                    if (integerValueOfViewModelDiarioChiloCaloriePranzo != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().chiloCaloriePranzo).toString()
                        integerValueOfViewModelDiarioChiloCaloriePranzoToString = integerValueOfViewModelDiarioChiloCaloriePranzo.toString();
                    }
                    if (integerValueOfViewModelDiarioChiloCalorieEsercizio != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieEsercizio).toString()
                        integerValueOfViewModelDiarioChiloCalorieEsercizioToString = integerValueOfViewModelDiarioChiloCalorieEsercizio.toString();
                    }
                    if (integerValueOfViewModelDiarioProteineTot != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().proteineTot).toString()
                        integerValueOfViewModelDiarioProteineTotToString = integerValueOfViewModelDiarioProteineTot.toString();
                    }
                    if (integerValueOfViewModelDiarioChiloCalorieColazione != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieColazione).toString()
                        integerValueOfViewModelDiarioChiloCalorieColazioneToString = integerValueOfViewModelDiarioChiloCalorieColazione.toString();
                    }
                    if (integerValueOfViewModelDiarioCarboidratiTot != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().carboidratiTot).toString()
                        integerValueOfViewModelDiarioCarboidratiTotToString = integerValueOfViewModelDiarioCarboidratiTot.toString();
                    }
                    if (integerValueOfViewModelDiarioGrassiTot != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().grassiTot).toString()
                        integerValueOfViewModelDiarioGrassiTotToString = integerValueOfViewModelDiarioGrassiTot.toString();
                    }
                    if (integerValueOfViewModelDiarioChiloCalorieSpuntino != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieSpuntino).toString()
                        integerValueOfViewModelDiarioChiloCalorieSpuntinoToString = integerValueOfViewModelDiarioChiloCalorieSpuntino.toString();
                    }
                    if (integerValueOfViewModelDiarioChiloCalorieCena != null) {
                        // read Integer.valueOf(viewModel.diario.getValue().chiloCalorieCena).toString()
                        integerValueOfViewModelDiarioChiloCalorieCenaToString = integerValueOfViewModelDiarioChiloCalorieCena.toString();
                    }
            }
            if ((dirtyFlags & 0x184L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.proteineMax
                        viewModelProteineMax = viewModel.getProteineMax();
                    }
                    updateLiveDataRegistration(2, viewModelProteineMax);


                    if (viewModelProteineMax != null) {
                        // read viewModel.proteineMax.getValue()
                        viewModelProteineMaxGetValue = viewModelProteineMax.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.proteineMax.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelProteineMaxGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelProteineMaxGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.proteineMax.getValue()))
                    integerValueOfViewModelProteineMax = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelProteineMaxGetValue);


                    if (integerValueOfViewModelProteineMax != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.proteineMax.getValue())).toString()
                        integerValueOfViewModelProteineMaxToString = integerValueOfViewModelProteineMax.toString();
                    }
            }
            if ((dirtyFlags & 0x188L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.result
                        viewModelResult = viewModel.getResult();
                    }
                    updateLiveDataRegistration(3, viewModelResult);


                    if (viewModelResult != null) {
                        // read viewModel.result.getValue()
                        viewModelResultGetValue = viewModelResult.getValue();
                    }
            }
            if ((dirtyFlags & 0x190L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.carboidratiMax
                        viewModelCarboidratiMax = viewModel.getCarboidratiMax();
                    }
                    updateLiveDataRegistration(4, viewModelCarboidratiMax);


                    if (viewModelCarboidratiMax != null) {
                        // read viewModel.carboidratiMax.getValue()
                        viewModelCarboidratiMaxGetValue = viewModelCarboidratiMax.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.carboidratiMax.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCarboidratiMaxGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCarboidratiMaxGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.carboidratiMax.getValue()))
                    integerValueOfViewModelCarboidratiMax = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCarboidratiMaxGetValue);


                    if (integerValueOfViewModelCarboidratiMax != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.carboidratiMax.getValue())).toString()
                        integerValueOfViewModelCarboidratiMaxToString = integerValueOfViewModelCarboidratiMax.toString();
                    }
            }
            if ((dirtyFlags & 0x1a0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.acqua
                        viewModelAcqua = viewModel.getAcqua();
                    }
                    updateLiveDataRegistration(5, viewModelAcqua);


                    if (viewModelAcqua != null) {
                        // read viewModel.acqua.getValue()
                        viewModelAcquaGetValue = viewModelAcqua.getValue();
                    }
            }
            if ((dirtyFlags & 0x1c0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.grassiMax
                        viewModelGrassiMax = viewModel.getGrassiMax();
                    }
                    updateLiveDataRegistration(6, viewModelGrassiMax);


                    if (viewModelGrassiMax != null) {
                        // read viewModel.grassiMax.getValue()
                        viewModelGrassiMaxGetValue = viewModelGrassiMax.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grassiMax.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGrassiMaxGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGrassiMaxGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grassiMax.getValue()))
                    integerValueOfViewModelGrassiMax = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelGrassiMaxGetValue);


                    if (integerValueOfViewModelGrassiMax != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grassiMax.getValue())).toString()
                        integerValueOfViewModelGrassiMaxToString = integerValueOfViewModelGrassiMax.toString();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x181L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.calAssunte, viewModelAssunteGetValue);
        }
        if ((dirtyFlags & 0x182L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.calBruciate, integerValueOfViewModelDiarioChiloCalorieEsercizioToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.kcalCena, integerValueOfViewModelDiarioChiloCalorieCenaToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.kcalColazione, integerValueOfViewModelDiarioChiloCalorieColazioneToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.kcalEsercizio, integerValueOfViewModelDiarioChiloCalorieEsercizioToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.kcalPranzo, integerValueOfViewModelDiarioChiloCaloriePranzoToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.kcalSpuntino, integerValueOfViewModelDiarioChiloCalorieSpuntinoToString);
            this.pgBarCarboidrati.setProgress(viewModelDiarioCarboidratiTot);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.qntCarboidrati, integerValueOfViewModelDiarioCarboidratiTotToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.qntGrassi, integerValueOfViewModelDiarioGrassiTotToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.qntProteine, integerValueOfViewModelDiarioProteineTotToString);
        }
        if ((dirtyFlags & 0x188L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.calorieRimanenti, viewModelResultGetValue);
        }
        if ((dirtyFlags & 0x190L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.qntCarboMAX, integerValueOfViewModelCarboidratiMaxToString);
        }
        if ((dirtyFlags & 0x1c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.qntGrsMAX, integerValueOfViewModelGrassiMaxToString);
        }
        if ((dirtyFlags & 0x184L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.qntProMAX, integerValueOfViewModelProteineMaxToString);
        }
        if ((dirtyFlags & 0x1a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textView25, viewModelAcquaGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.assunte
        flag 1 (0x2L): viewModel.diario
        flag 2 (0x3L): viewModel.proteineMax
        flag 3 (0x4L): viewModel.result
        flag 4 (0x5L): viewModel.carboidratiMax
        flag 5 (0x6L): viewModel.acqua
        flag 6 (0x7L): viewModel.grassiMax
        flag 7 (0x8L): viewModel
        flag 8 (0x9L): null
    flag mapping end*/
    //end
}