package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentStileVitaBindingImpl extends FragmentStileVitaBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textView4, 1);
        sViewsWithIds.put(R.id.GruppoRadio_Obbiettivo, 2);
        sViewsWithIds.put(R.id.rB_sedentario, 3);
        sViewsWithIds.put(R.id.rB_pocoattivo, 4);
        sViewsWithIds.put(R.id.rB_attivo, 5);
        sViewsWithIds.put(R.id.rB_moltoattivo, 6);
        sViewsWithIds.put(R.id.bt_AvantiObb, 7);
        sViewsWithIds.put(R.id.linearLayout2, 8);
        sViewsWithIds.put(R.id.imageView4, 9);
        sViewsWithIds.put(R.id.textView13, 10);
        sViewsWithIds.put(R.id.liniette, 11);
        sViewsWithIds.put(R.id.imageView10, 12);
        sViewsWithIds.put(R.id.imageView11, 13);
        sViewsWithIds.put(R.id.imageView12, 14);
        sViewsWithIds.put(R.id.imageView13, 15);
        sViewsWithIds.put(R.id.imageView14, 16);
        sViewsWithIds.put(R.id.imageView15, 17);
        sViewsWithIds.put(R.id.imageView16, 18);
        sViewsWithIds.put(R.id.sB_agonistico, 19);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentStileVitaBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private FragmentStileVitaBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.RadioGroup) bindings[2]
            , (android.widget.Button) bindings[7]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[18]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.RadioButton) bindings[5]
            , (android.widget.RadioButton) bindings[6]
            , (android.widget.RadioButton) bindings[4]
            , (android.widget.RadioButton) bindings[3]
            , (android.widget.Switch) bindings[19]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}