package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProfiloBindingImpl extends ActivityProfiloBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textView12, 5);
        sViewsWithIds.put(R.id.textView3238, 6);
        sViewsWithIds.put(R.id.textView29, 7);
        sViewsWithIds.put(R.id.textView248, 8);
        sViewsWithIds.put(R.id.sW_sesso, 9);
        sViewsWithIds.put(R.id.textView25, 10);
        sViewsWithIds.put(R.id.password, 11);
        sViewsWithIds.put(R.id.textView88, 12);
        sViewsWithIds.put(R.id.btn_cambio_pass, 13);
        sViewsWithIds.put(R.id.data_di_nascita, 14);
        sViewsWithIds.put(R.id.textView30, 15);
        sViewsWithIds.put(R.id.textView81, 16);
        sViewsWithIds.put(R.id.btn_cambio_intolleranze, 17);
        sViewsWithIds.put(R.id.btn_salva, 18);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProfiloBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private ActivityProfiloBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[17]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[18]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[2]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.Spinner) bindings[9]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[12]
            );
        this.eTCambioEmail.setTag(null);
        this.eTNome.setTag(null);
        this.etCognome.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tVDataNascita.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setViewModel((com.example.letmebeyourchef.profilo.ProfiloViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.letmebeyourchef.profilo.ProfiloViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelProfilo((androidx.lifecycle.LiveData<com.example.letmebeyourchef.model.Utente>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelProfilo(androidx.lifecycle.LiveData<com.example.letmebeyourchef.model.Utente> ViewModelProfilo, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        androidx.lifecycle.LiveData<com.example.letmebeyourchef.model.Utente> viewModelProfilo = null;
        com.example.letmebeyourchef.model.Utente viewModelProfiloGetValue = null;
        java.lang.String viewModelProfiloCognome = null;
        java.lang.String viewModelProfiloDataNascita = null;
        java.lang.String viewModelProfiloEmail = null;
        java.lang.String viewModelProfiloNome = null;
        com.example.letmebeyourchef.profilo.ProfiloViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.profilo
                    viewModelProfilo = viewModel.getProfilo();
                }
                updateLiveDataRegistration(0, viewModelProfilo);


                if (viewModelProfilo != null) {
                    // read viewModel.profilo.getValue()
                    viewModelProfiloGetValue = viewModelProfilo.getValue();
                }


                if (viewModelProfiloGetValue != null) {
                    // read viewModel.profilo.getValue().cognome
                    viewModelProfiloCognome = viewModelProfiloGetValue.getCognome();
                    // read viewModel.profilo.getValue().data_nascita
                    viewModelProfiloDataNascita = viewModelProfiloGetValue.getData_nascita();
                    // read viewModel.profilo.getValue().email
                    viewModelProfiloEmail = viewModelProfiloGetValue.getEmail();
                    // read viewModel.profilo.getValue().nome
                    viewModelProfiloNome = viewModelProfiloGetValue.getNome();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.eTCambioEmail, viewModelProfiloEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.eTNome, viewModelProfiloNome);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etCognome, viewModelProfiloCognome);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tVDataNascita, viewModelProfiloDataNascita);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.profilo
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}