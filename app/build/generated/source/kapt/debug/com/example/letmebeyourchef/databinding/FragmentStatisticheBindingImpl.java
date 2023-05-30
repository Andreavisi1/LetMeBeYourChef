package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentStatisticheBindingImpl extends FragmentStatisticheBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout8, 7);
        sViewsWithIds.put(R.id.coek, 8);
        sViewsWithIds.put(R.id.tV_dataiInizio, 9);
        sViewsWithIds.put(R.id.te16, 10);
        sViewsWithIds.put(R.id.tV_dataFine, 11);
        sViewsWithIds.put(R.id.btn_filtra, 12);
        sViewsWithIds.put(R.id.btn_reset, 13);
        sViewsWithIds.put(R.id.progress, 14);
        sViewsWithIds.put(R.id.tdfiew31, 15);
        sViewsWithIds.put(R.id.twff31, 16);
        sViewsWithIds.put(R.id.textVff31, 17);
        sViewsWithIds.put(R.id.tevfd31, 18);
        sViewsWithIds.put(R.id.tvffdw31, 19);
        sViewsWithIds.put(R.id.tvfvew31, 20);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentStatisticheBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private FragmentStatisticheBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[13]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[1]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.TextView) bindings[2]
            , (android.widget.ProgressBar) bindings[14]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[16]
            );
        this.giorni2Litri.setTag(null);
        this.grCarbo.setTag(null);
        this.grGrassi.setTag(null);
        this.grProt.setTag(null);
        this.kcalConsumate.setTag(null);
        this.litriConsumati.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
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
            setViewModel((com.example.letmebeyourchef.statistiche.StatisticheViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.letmebeyourchef.statistiche.StatisticheViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelKcalAssunte((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 1 :
                return onChangeViewModelGrGrassi((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 2 :
                return onChangeViewModelGiorniAcqua((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 3 :
                return onChangeViewModelLitriBevuti((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 4 :
                return onChangeViewModelGrCarboidrati((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 5 :
                return onChangeViewModelGrProteine((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelKcalAssunte(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelKcalAssunte, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGrGrassi(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelGrGrassi, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGiorniAcqua(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelGiorniAcqua, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelLitriBevuti(androidx.lifecycle.LiveData<java.lang.Double> ViewModelLitriBevuti, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGrCarboidrati(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelGrCarboidrati, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGrProteine(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelGrProteine, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
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
        int androidxDatabindingViewDataBindingSafeUnboxViewModelGrCarboidratiGetValue = 0;
        java.lang.Integer integerValueOfViewModelGiorniAcqua = null;
        java.lang.Integer viewModelGrProteineGetValue = null;
        java.lang.Double doubleValueOfViewModelLitriBevuti = null;
        java.lang.Integer integerValueOfViewModelGrCarboidrati = null;
        java.lang.String integerValueOfViewModelGrGrassiToString = null;
        java.lang.Double viewModelLitriBevutiGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelKcalAssunte = null;
        java.lang.String integerValueOfViewModelGrCarboidratiToString = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelGrGrassi = null;
        java.lang.Integer viewModelGrCarboidratiGetValue = null;
        java.lang.String integerValueOfViewModelGiorniAcquaToString = null;
        java.lang.String doubleValueOfViewModelLitriBevutiToString = null;
        java.lang.Integer integerValueOfViewModelGrGrassi = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelGiorniAcqua = null;
        double androidxDatabindingViewDataBindingSafeUnboxViewModelLitriBevutiGetValue = 0.0;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelLitriBevuti = null;
        java.lang.Integer integerValueOfViewModelKcalAssunte = null;
        java.lang.String integerValueOfViewModelKcalAssunteToString = null;
        java.lang.Integer viewModelGiorniAcquaGetValue = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelGiorniAcquaGetValue = 0;
        java.lang.String integerValueOfViewModelGrProteineToString = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelGrProteineGetValue = 0;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelGrGrassiGetValue = 0;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelGrCarboidrati = null;
        java.lang.Integer viewModelGrGrassiGetValue = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelKcalAssunteGetValue = 0;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelGrProteine = null;
        java.lang.Integer integerValueOfViewModelGrProteine = null;
        com.example.letmebeyourchef.statistiche.StatisticheViewModel viewModel = mViewModel;
        java.lang.Integer viewModelKcalAssunteGetValue = null;

        if ((dirtyFlags & 0xffL) != 0) {


            if ((dirtyFlags & 0xc1L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.kcalAssunte
                        viewModelKcalAssunte = viewModel.getKcalAssunte();
                    }
                    updateLiveDataRegistration(0, viewModelKcalAssunte);


                    if (viewModelKcalAssunte != null) {
                        // read viewModel.kcalAssunte.getValue()
                        viewModelKcalAssunteGetValue = viewModelKcalAssunte.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.kcalAssunte.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelKcalAssunteGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelKcalAssunteGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.kcalAssunte.getValue()))
                    integerValueOfViewModelKcalAssunte = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelKcalAssunteGetValue);


                    if (integerValueOfViewModelKcalAssunte != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.kcalAssunte.getValue())).toString()
                        integerValueOfViewModelKcalAssunteToString = integerValueOfViewModelKcalAssunte.toString();
                    }
            }
            if ((dirtyFlags & 0xc2L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.grGrassi
                        viewModelGrGrassi = viewModel.getGrGrassi();
                    }
                    updateLiveDataRegistration(1, viewModelGrGrassi);


                    if (viewModelGrGrassi != null) {
                        // read viewModel.grGrassi.getValue()
                        viewModelGrGrassiGetValue = viewModelGrGrassi.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grGrassi.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGrGrassiGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGrGrassiGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grGrassi.getValue()))
                    integerValueOfViewModelGrGrassi = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelGrGrassiGetValue);


                    if (integerValueOfViewModelGrGrassi != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grGrassi.getValue())).toString()
                        integerValueOfViewModelGrGrassiToString = integerValueOfViewModelGrGrassi.toString();
                    }
            }
            if ((dirtyFlags & 0xc4L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.giorniAcqua
                        viewModelGiorniAcqua = viewModel.getGiorniAcqua();
                    }
                    updateLiveDataRegistration(2, viewModelGiorniAcqua);


                    if (viewModelGiorniAcqua != null) {
                        // read viewModel.giorniAcqua.getValue()
                        viewModelGiorniAcquaGetValue = viewModelGiorniAcqua.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.giorniAcqua.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGiorniAcquaGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGiorniAcquaGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.giorniAcqua.getValue()))
                    integerValueOfViewModelGiorniAcqua = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelGiorniAcquaGetValue);


                    if (integerValueOfViewModelGiorniAcqua != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.giorniAcqua.getValue())).toString()
                        integerValueOfViewModelGiorniAcquaToString = integerValueOfViewModelGiorniAcqua.toString();
                    }
            }
            if ((dirtyFlags & 0xc8L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.litriBevuti
                        viewModelLitriBevuti = viewModel.getLitriBevuti();
                    }
                    updateLiveDataRegistration(3, viewModelLitriBevuti);


                    if (viewModelLitriBevuti != null) {
                        // read viewModel.litriBevuti.getValue()
                        viewModelLitriBevutiGetValue = viewModelLitriBevuti.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.litriBevuti.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelLitriBevutiGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelLitriBevutiGetValue);


                    // read Double.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.litriBevuti.getValue()))
                    doubleValueOfViewModelLitriBevuti = java.lang.Double.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelLitriBevutiGetValue);


                    if (doubleValueOfViewModelLitriBevuti != null) {
                        // read Double.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.litriBevuti.getValue())).toString()
                        doubleValueOfViewModelLitriBevutiToString = doubleValueOfViewModelLitriBevuti.toString();
                    }
            }
            if ((dirtyFlags & 0xd0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.grCarboidrati
                        viewModelGrCarboidrati = viewModel.getGrCarboidrati();
                    }
                    updateLiveDataRegistration(4, viewModelGrCarboidrati);


                    if (viewModelGrCarboidrati != null) {
                        // read viewModel.grCarboidrati.getValue()
                        viewModelGrCarboidratiGetValue = viewModelGrCarboidrati.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grCarboidrati.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGrCarboidratiGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGrCarboidratiGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grCarboidrati.getValue()))
                    integerValueOfViewModelGrCarboidrati = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelGrCarboidratiGetValue);


                    if (integerValueOfViewModelGrCarboidrati != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grCarboidrati.getValue())).toString()
                        integerValueOfViewModelGrCarboidratiToString = integerValueOfViewModelGrCarboidrati.toString();
                    }
            }
            if ((dirtyFlags & 0xe0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.grProteine
                        viewModelGrProteine = viewModel.getGrProteine();
                    }
                    updateLiveDataRegistration(5, viewModelGrProteine);


                    if (viewModelGrProteine != null) {
                        // read viewModel.grProteine.getValue()
                        viewModelGrProteineGetValue = viewModelGrProteine.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grProteine.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGrProteineGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGrProteineGetValue);


                    // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grProteine.getValue()))
                    integerValueOfViewModelGrProteine = java.lang.Integer.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelGrProteineGetValue);


                    if (integerValueOfViewModelGrProteine != null) {
                        // read Integer.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.grProteine.getValue())).toString()
                        integerValueOfViewModelGrProteineToString = integerValueOfViewModelGrProteine.toString();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xc4L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.giorni2Litri, integerValueOfViewModelGiorniAcquaToString);
        }
        if ((dirtyFlags & 0xd0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.grCarbo, integerValueOfViewModelGrCarboidratiToString);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.grGrassi, integerValueOfViewModelGrGrassiToString);
        }
        if ((dirtyFlags & 0xe0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.grProt, integerValueOfViewModelGrProteineToString);
        }
        if ((dirtyFlags & 0xc1L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.kcalConsumate, integerValueOfViewModelKcalAssunteToString);
        }
        if ((dirtyFlags & 0xc8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.litriConsumati, doubleValueOfViewModelLitriBevutiToString);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.kcalAssunte
        flag 1 (0x2L): viewModel.grGrassi
        flag 2 (0x3L): viewModel.giorniAcqua
        flag 3 (0x4L): viewModel.litriBevuti
        flag 4 (0x5L): viewModel.grCarboidrati
        flag 5 (0x6L): viewModel.grProteine
        flag 6 (0x7L): viewModel
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}