package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProdottoBindingImpl extends ActivityProdottoBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout6, 1);
        sViewsWithIds.put(R.id.imageProdotto, 2);
        sViewsWithIds.put(R.id.tvProductName, 3);
        sViewsWithIds.put(R.id.textView51, 4);
        sViewsWithIds.put(R.id.textView34, 5);
        sViewsWithIds.put(R.id.tvCalorie, 6);
        sViewsWithIds.put(R.id.textView46, 7);
        sViewsWithIds.put(R.id.tvCarboidrati, 8);
        sViewsWithIds.put(R.id.textView49, 9);
        sViewsWithIds.put(R.id.tvGrassi, 10);
        sViewsWithIds.put(R.id.textView37, 11);
        sViewsWithIds.put(R.id.tvProteine, 12);
        sViewsWithIds.put(R.id.textView52, 13);
        sViewsWithIds.put(R.id.textView54, 14);
        sViewsWithIds.put(R.id.tvBrand, 15);
        sViewsWithIds.put(R.id.textView55, 16);
        sViewsWithIds.put(R.id.tvCategory, 17);
        sViewsWithIds.put(R.id.textView57, 18);
        sViewsWithIds.put(R.id.tvDescription, 19);
        sViewsWithIds.put(R.id.textView58, 20);
        sViewsWithIds.put(R.id.etQuantita, 21);
        sViewsWithIds.put(R.id.btnAddDiary, 22);
        sViewsWithIds.put(R.id.btnAddPreferiti, 23);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProdottoBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private ActivityProdottoBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[22]
            , (android.widget.Button) bindings[23]
            , (android.widget.EditText) bindings[21]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[12]
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