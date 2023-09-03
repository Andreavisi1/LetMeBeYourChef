package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSavedPlaceBindingImpl extends FragmentSavedPlaceBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textViewWorkInProgress, 2);
        sViewsWithIds.put(R.id.imageViewProgrammer, 3);
        sViewsWithIds.put(R.id.textMeanwhile, 4);
        sViewsWithIds.put(R.id.imageView71, 5);
        sViewsWithIds.put(R.id.savedRecyclerView, 6);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSavedPlaceBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private FragmentSavedPlaceBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.ImageView) bindings[3]
            , (androidx.recyclerview.widget.RecyclerView) bindings[6]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[2]
            );
        this.imageView74.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.googlePlaceModel == variableId) {
            setGooglePlaceModel((com.example.letmebeyourchef.model.GooglePlaceModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setGooglePlaceModel(@Nullable com.example.letmebeyourchef.model.GooglePlaceModel GooglePlaceModel) {
        this.mGooglePlaceModel = GooglePlaceModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.googlePlaceModel);
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
        java.lang.Boolean googlePlaceModelSaved = null;
        com.example.letmebeyourchef.model.GooglePlaceModel googlePlaceModel = mGooglePlaceModel;
        boolean androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelSaved = false;
        boolean googlePlaceModelSavedBooleanTrue = false;
        android.graphics.drawable.Drawable googlePlaceModelSavedBooleanTrueImageView74AndroidDrawableIcBookmarkedImageView74AndroidDrawableIcBookmark = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (googlePlaceModel != null) {
                    // read googlePlaceModel.saved
                    googlePlaceModelSaved = googlePlaceModel.getSaved();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved)
                androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelSaved = androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModelSaved);


                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true
                googlePlaceModelSavedBooleanTrue = (androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelSaved) == (true);
            if((dirtyFlags & 0x3L) != 0) {
                if(googlePlaceModelSavedBooleanTrue) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true ? @android:drawable/ic_bookmarked : @android:drawable/ic_bookmark
                googlePlaceModelSavedBooleanTrueImageView74AndroidDrawableIcBookmarkedImageView74AndroidDrawableIcBookmark = ((googlePlaceModelSavedBooleanTrue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(imageView74.getContext(), R.drawable.ic_bookmarked)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(imageView74.getContext(), R.drawable.ic_bookmark)));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.imageView74, googlePlaceModelSavedBooleanTrueImageView74AndroidDrawableIcBookmarkedImageView74AndroidDrawableIcBookmark);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): googlePlaceModel
        flag 1 (0x2L): null
        flag 2 (0x3L): androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true ? @android:drawable/ic_bookmarked : @android:drawable/ic_bookmark
        flag 3 (0x4L): androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true ? @android:drawable/ic_bookmarked : @android:drawable/ic_bookmark
    flag mapping end*/
    //end
}