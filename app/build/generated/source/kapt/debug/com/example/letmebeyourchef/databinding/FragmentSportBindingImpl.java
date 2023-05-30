package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSportBindingImpl extends FragmentSportBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout16, 1);
        sViewsWithIds.put(R.id.imageView46, 2);
        sViewsWithIds.put(R.id.imageView47, 3);
        sViewsWithIds.put(R.id.imageView48, 4);
        sViewsWithIds.put(R.id.imageView49, 5);
        sViewsWithIds.put(R.id.imageView50, 6);
        sViewsWithIds.put(R.id.imageView54, 7);
        sViewsWithIds.put(R.id.imageView53, 8);
        sViewsWithIds.put(R.id.textView11, 9);
        sViewsWithIds.put(R.id.GruppoRadio_Sport, 10);
        sViewsWithIds.put(R.id.cB_calcio, 11);
        sViewsWithIds.put(R.id.cB_basket, 12);
        sViewsWithIds.put(R.id.cB_pallavolo, 13);
        sViewsWithIds.put(R.id.cB_nuoto, 14);
        sViewsWithIds.put(R.id.cB_baseball, 15);
        sViewsWithIds.put(R.id.cB_karate, 16);
        sViewsWithIds.put(R.id.cB_pattinaggio, 17);
        sViewsWithIds.put(R.id.cB_cricket, 18);
        sViewsWithIds.put(R.id.cB_tennis, 19);
        sViewsWithIds.put(R.id.cB_hockey, 20);
        sViewsWithIds.put(R.id.cB_ping_pong, 21);
        sViewsWithIds.put(R.id.cB_golf, 22);
        sViewsWithIds.put(R.id.cB_rugby, 23);
        sViewsWithIds.put(R.id.cB_football, 24);
        sViewsWithIds.put(R.id.cB_atletica, 25);
        sViewsWithIds.put(R.id.cB_ginnastica_art, 26);
        sViewsWithIds.put(R.id.cB_ginnastica_ritm, 27);
        sViewsWithIds.put(R.id.cB_ciclismo, 28);
        sViewsWithIds.put(R.id.cB_judo, 29);
        sViewsWithIds.put(R.id.cB_pallanuoto, 30);
        sViewsWithIds.put(R.id.cB_altro, 31);
        sViewsWithIds.put(R.id.bt_AvantiPesoObb, 32);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSportBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }
    private FragmentSportBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.RadioGroup) bindings[10]
            , (android.widget.Button) bindings[32]
            , (android.widget.RadioButton) bindings[31]
            , (android.widget.RadioButton) bindings[25]
            , (android.widget.RadioButton) bindings[15]
            , (android.widget.RadioButton) bindings[12]
            , (android.widget.RadioButton) bindings[11]
            , (android.widget.RadioButton) bindings[28]
            , (android.widget.RadioButton) bindings[18]
            , (android.widget.RadioButton) bindings[24]
            , (android.widget.RadioButton) bindings[26]
            , (android.widget.RadioButton) bindings[27]
            , (android.widget.RadioButton) bindings[22]
            , (android.widget.RadioButton) bindings[20]
            , (android.widget.RadioButton) bindings[29]
            , (android.widget.RadioButton) bindings[16]
            , (android.widget.RadioButton) bindings[14]
            , (android.widget.RadioButton) bindings[30]
            , (android.widget.RadioButton) bindings[13]
            , (android.widget.RadioButton) bindings[17]
            , (android.widget.RadioButton) bindings[21]
            , (android.widget.RadioButton) bindings[23]
            , (android.widget.RadioButton) bindings[19]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[9]
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