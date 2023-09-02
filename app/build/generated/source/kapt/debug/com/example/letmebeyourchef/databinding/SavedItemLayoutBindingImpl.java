package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SavedItemLayoutBindingImpl extends SavedItemLayoutBinding implements com.example.letmebeyourchef.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.placeLayout, 4);
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView2;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SavedItemLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private SavedItemLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.ImageView) bindings[2];
        this.mboundView2.setTag(null);
        this.txtPlaceAddress.setTag(null);
        this.txtPlaceName.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.example.letmebeyourchef.generated.callback.OnClickListener(this, 1);
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
        if (BR.savedPlaceModel == variableId) {
            setSavedPlaceModel((com.example.letmebeyourchef.model.SavedPlaceModel) variable);
        }
        else if (BR.listener == variableId) {
            setListener((com.example.letmebeyourchef.maps.SaveLocationInterface) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSavedPlaceModel(@Nullable com.example.letmebeyourchef.model.SavedPlaceModel SavedPlaceModel) {
        this.mSavedPlaceModel = SavedPlaceModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.savedPlaceModel);
        super.requestRebind();
    }
    public void setListener(@Nullable com.example.letmebeyourchef.maps.SaveLocationInterface Listener) {
        this.mListener = Listener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.listener);
        super.requestRebind();
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
        com.example.letmebeyourchef.model.SavedPlaceModel savedPlaceModel = mSavedPlaceModel;
        java.lang.String savedPlaceModelAddress = null;
        com.example.letmebeyourchef.maps.SaveLocationInterface listener = mListener;
        java.lang.String savedPlaceModelName = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (savedPlaceModel != null) {
                    // read savedPlaceModel.address
                    savedPlaceModelAddress = savedPlaceModel.getAddress();
                    // read savedPlaceModel.name
                    savedPlaceModelName = savedPlaceModel.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView2.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtPlaceAddress, savedPlaceModelAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtPlaceName, savedPlaceModelName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // savedPlaceModel
        com.example.letmebeyourchef.model.SavedPlaceModel savedPlaceModel = mSavedPlaceModel;
        // listener != null
        boolean listenerJavaLangObjectNull = false;
        // listener
        com.example.letmebeyourchef.maps.SaveLocationInterface listener = mListener;



        listenerJavaLangObjectNull = (listener) != (null);
        if (listenerJavaLangObjectNull) {



            listener.onLocationClick(savedPlaceModel);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): savedPlaceModel
        flag 1 (0x2L): listener
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}