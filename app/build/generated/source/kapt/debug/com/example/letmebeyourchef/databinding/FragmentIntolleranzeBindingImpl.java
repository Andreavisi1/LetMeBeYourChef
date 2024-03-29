package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentIntolleranzeBindingImpl extends FragmentIntolleranzeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout16, 1);
        sViewsWithIds.put(R.id.imageView6, 2);
        sViewsWithIds.put(R.id.imageView9, 3);
        sViewsWithIds.put(R.id.imageView17, 4);
        sViewsWithIds.put(R.id.imageView18, 5);
        sViewsWithIds.put(R.id.sB_intolleranze, 6);
        sViewsWithIds.put(R.id.scrollView3, 7);
        sViewsWithIds.put(R.id.checkboxes_layout, 8);
        sViewsWithIds.put(R.id.cB_dairy, 9);
        sViewsWithIds.put(R.id.cB_gluten, 10);
        sViewsWithIds.put(R.id.cB_peanut, 11);
        sViewsWithIds.put(R.id.cB_grain, 12);
        sViewsWithIds.put(R.id.cB_seafood, 13);
        sViewsWithIds.put(R.id.cB_egg, 14);
        sViewsWithIds.put(R.id.cB_sesame, 15);
        sViewsWithIds.put(R.id.cB_shellfish, 16);
        sViewsWithIds.put(R.id.cB_soy, 17);
        sViewsWithIds.put(R.id.cB_sulfite, 18);
        sViewsWithIds.put(R.id.cB_tree_nut, 19);
        sViewsWithIds.put(R.id.cB_wheat, 20);
        sViewsWithIds.put(R.id.cB_altro, 21);
        sViewsWithIds.put(R.id.RadioGroup_Intolleranze, 22);
        sViewsWithIds.put(R.id.ifnot, 23);
        sViewsWithIds.put(R.id.bt_AvantiIntolleranze, 24);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentIntolleranzeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private FragmentIntolleranzeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.RadioGroup) bindings[22]
            , (android.widget.Button) bindings[24]
            , (android.widget.CheckBox) bindings[21]
            , (android.widget.CheckBox) bindings[9]
            , (android.widget.CheckBox) bindings[14]
            , (android.widget.CheckBox) bindings[10]
            , (android.widget.CheckBox) bindings[12]
            , (android.widget.CheckBox) bindings[11]
            , (android.widget.CheckBox) bindings[13]
            , (android.widget.CheckBox) bindings[15]
            , (android.widget.CheckBox) bindings[16]
            , (android.widget.CheckBox) bindings[17]
            , (android.widget.CheckBox) bindings[18]
            , (android.widget.CheckBox) bindings[19]
            , (android.widget.CheckBox) bindings[20]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.TextView) bindings[23]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[6]
            , (android.widget.ScrollView) bindings[7]
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