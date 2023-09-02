package com.example.letmebeyourchef.databinding;
import com.example.letmebeyourchef.R;
import com.example.letmebeyourchef.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PlaceItemLayoutBindingImpl extends PlaceItemLayoutBinding implements com.example.letmebeyourchef.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rating, 8);
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final com.google.android.material.textview.MaterialTextView mboundView3;
    @NonNull
    private final android.widget.ImageView mboundView5;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PlaceItemLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private PlaceItemLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[4]
            , (android.widget.RelativeLayout) bindings[8]
            , (android.widget.TextView) bindings[7]
            , (com.google.android.material.textview.MaterialTextView) bindings[2]
            , (android.widget.TextView) bindings[6]
            );
        this.imgSaveLocation.setTag(null);
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView3 = (com.google.android.material.textview.MaterialTextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView5 = (android.widget.ImageView) bindings[5];
        this.mboundView5.setTag(null);
        this.txtPlaceAddress.setTag(null);
        this.txtPlaceDRating.setTag(null);
        this.txtPlaceName.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.example.letmebeyourchef.generated.callback.OnClickListener(this, 1);
        mCallback3 = new com.example.letmebeyourchef.generated.callback.OnClickListener(this, 2);
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
        if (BR.googlePlaceModel == variableId) {
            setGooglePlaceModel((com.example.letmebeyourchef.model.GooglePlaceModel) variable);
        }
        else if (BR.listener == variableId) {
            setListener((com.example.letmebeyourchef.maps.NearLocationInterface) variable);
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
    public void setListener(@Nullable com.example.letmebeyourchef.maps.NearLocationInterface Listener) {
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
        java.lang.Boolean googlePlaceModelSaved = null;
        java.lang.String javaLangStringStringValueOfGooglePlaceModelUserRatingsTotalJavaLangString = null;
        java.lang.String googlePlaceModelUserRatingsTotalJavaLangObjectNullJavaLangStringStringValueOfGooglePlaceModelUserRatingsTotalJavaLangStringJavaLangString = null;
        boolean googlePlaceModelVicinityJavaLangObjectNull = false;
        java.lang.Double googlePlaceModelRating = null;
        double androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelRating = 0.0;
        java.lang.String googlePlaceModelName = null;
        com.example.letmebeyourchef.model.GooglePlaceModel googlePlaceModel = mGooglePlaceModel;
        java.lang.String googlePlaceModelVicinity = null;
        java.lang.String googlePlaceModelIcon = null;
        java.lang.String javaLangStringStringValueOfGooglePlaceModelUserRatingsTotal = null;
        boolean googlePlaceModelNameJavaLangObjectNull = false;
        java.lang.String googlePlaceModelVicinityJavaLangObjectNullGooglePlaceModelVicinityJavaLangStringNoAddress = null;
        com.example.letmebeyourchef.maps.NearLocationInterface listener = mListener;
        boolean androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelSaved = false;
        boolean googlePlaceModelSavedBooleanTrue = false;
        java.lang.String stringValueOfGooglePlaceModelRating = null;
        java.lang.String googlePlaceModelNameJavaLangObjectNullGooglePlaceModelNameJavaLangStringNoName = null;
        boolean googlePlaceModelRatingJavaLangObjectNull = false;
        java.lang.Integer googlePlaceModelUserRatingsTotal = null;
        int androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelUserRatingsTotal = 0;
        boolean googlePlaceModelUserRatingsTotalJavaLangObjectNull = false;
        java.lang.String googlePlaceModelRatingJavaLangObjectNullStringValueOfGooglePlaceModelRatingJavaLangStringNew = null;
        java.lang.String stringValueOfGooglePlaceModelUserRatingsTotal = null;
        android.graphics.drawable.Drawable googlePlaceModelSavedBooleanTrueImgSaveLocationAndroidDrawableIcBookmarkedImgSaveLocationAndroidDrawableIcBookmark = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (googlePlaceModel != null) {
                    // read googlePlaceModel.saved
                    googlePlaceModelSaved = googlePlaceModel.getSaved();
                    // read googlePlaceModel.rating
                    googlePlaceModelRating = googlePlaceModel.getRating();
                    // read googlePlaceModel.name
                    googlePlaceModelName = googlePlaceModel.getName();
                    // read googlePlaceModel.vicinity
                    googlePlaceModelVicinity = googlePlaceModel.getVicinity();
                    // read googlePlaceModel.icon
                    googlePlaceModelIcon = googlePlaceModel.getIcon();
                    // read googlePlaceModel.userRatingsTotal
                    googlePlaceModelUserRatingsTotal = googlePlaceModel.getUserRatingsTotal();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved)
                androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelSaved = androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModelSaved);
                // read googlePlaceModel.rating != null
                googlePlaceModelRatingJavaLangObjectNull = (googlePlaceModelRating) != (null);
                // read googlePlaceModel.name != null
                googlePlaceModelNameJavaLangObjectNull = (googlePlaceModelName) != (null);
                // read googlePlaceModel.vicinity != null
                googlePlaceModelVicinityJavaLangObjectNull = (googlePlaceModelVicinity) != (null);
                // read googlePlaceModel.userRatingsTotal != null
                googlePlaceModelUserRatingsTotalJavaLangObjectNull = (googlePlaceModelUserRatingsTotal) != (null);
            if((dirtyFlags & 0x5L) != 0) {
                if(googlePlaceModelRatingJavaLangObjectNull) {
                        dirtyFlags |= 0x400L;
                }
                else {
                        dirtyFlags |= 0x200L;
                }
            }
            if((dirtyFlags & 0x5L) != 0) {
                if(googlePlaceModelNameJavaLangObjectNull) {
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x80L;
                }
            }
            if((dirtyFlags & 0x5L) != 0) {
                if(googlePlaceModelVicinityJavaLangObjectNull) {
                        dirtyFlags |= 0x40L;
                }
                else {
                        dirtyFlags |= 0x20L;
                }
            }
            if((dirtyFlags & 0x5L) != 0) {
                if(googlePlaceModelUserRatingsTotalJavaLangObjectNull) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true
                googlePlaceModelSavedBooleanTrue = (androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelSaved) == (true);
            if((dirtyFlags & 0x5L) != 0) {
                if(googlePlaceModelSavedBooleanTrue) {
                        dirtyFlags |= 0x1000L;
                }
                else {
                        dirtyFlags |= 0x800L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true ? @android:drawable/ic_bookmarked : @android:drawable/ic_bookmark
                googlePlaceModelSavedBooleanTrueImgSaveLocationAndroidDrawableIcBookmarkedImgSaveLocationAndroidDrawableIcBookmark = ((googlePlaceModelSavedBooleanTrue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(imgSaveLocation.getContext(), R.drawable.ic_bookmarked)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(imgSaveLocation.getContext(), R.drawable.ic_bookmark)));
        }
        // batch finished

        if ((dirtyFlags & 0x400L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.rating)
                androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelRating = androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModelRating);


                // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.rating))
                stringValueOfGooglePlaceModelRating = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelRating);
        }
        if ((dirtyFlags & 0x5L) != 0) {

                // read googlePlaceModel.vicinity != null ? googlePlaceModel.vicinity : "No Address"
                googlePlaceModelVicinityJavaLangObjectNullGooglePlaceModelVicinityJavaLangStringNoAddress = ((googlePlaceModelVicinityJavaLangObjectNull) ? (googlePlaceModelVicinity) : ("No Address"));
                // read googlePlaceModel.name != null ? googlePlaceModel.name : "No Name"
                googlePlaceModelNameJavaLangObjectNullGooglePlaceModelNameJavaLangStringNoName = ((googlePlaceModelNameJavaLangObjectNull) ? (googlePlaceModelName) : ("No Name"));
        }
        if ((dirtyFlags & 0x10L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal)
                androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelUserRatingsTotal = androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModelUserRatingsTotal);


                // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal))
                stringValueOfGooglePlaceModelUserRatingsTotal = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxGooglePlaceModelUserRatingsTotal);


                // read ("(") + (String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal)))
                javaLangStringStringValueOfGooglePlaceModelUserRatingsTotal = ("(") + (stringValueOfGooglePlaceModelUserRatingsTotal);


                // read (("(") + (String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal)))) + (")")
                javaLangStringStringValueOfGooglePlaceModelUserRatingsTotalJavaLangString = (javaLangStringStringValueOfGooglePlaceModelUserRatingsTotal) + (")");
        }

        if ((dirtyFlags & 0x5L) != 0) {

                // read googlePlaceModel.userRatingsTotal != null ? (("(") + (String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal)))) + (")") : ""
                googlePlaceModelUserRatingsTotalJavaLangObjectNullJavaLangStringStringValueOfGooglePlaceModelUserRatingsTotalJavaLangStringJavaLangString = ((googlePlaceModelUserRatingsTotalJavaLangObjectNull) ? (javaLangStringStringValueOfGooglePlaceModelUserRatingsTotalJavaLangString) : (""));
                // read googlePlaceModel.rating != null ? String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.rating)) : "New"
                googlePlaceModelRatingJavaLangObjectNullStringValueOfGooglePlaceModelRatingJavaLangStringNew = ((googlePlaceModelRatingJavaLangObjectNull) ? (stringValueOfGooglePlaceModelRating) : ("New"));
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.imgSaveLocation.setOnClickListener(mCallback2);
            this.mboundView5.setOnClickListener(mCallback3);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.imgSaveLocation, googlePlaceModelSavedBooleanTrueImgSaveLocationAndroidDrawableIcBookmarkedImgSaveLocationAndroidDrawableIcBookmark);
            com.example.letmebeyourchef.model.PhotoModel.loadImage(this.mboundView1, googlePlaceModelIcon);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, googlePlaceModelUserRatingsTotalJavaLangObjectNullJavaLangStringStringValueOfGooglePlaceModelUserRatingsTotalJavaLangStringJavaLangString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtPlaceAddress, googlePlaceModelVicinityJavaLangObjectNullGooglePlaceModelVicinityJavaLangStringNoAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtPlaceDRating, googlePlaceModelRatingJavaLangObjectNullStringValueOfGooglePlaceModelRatingJavaLangStringNew);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtPlaceName, googlePlaceModelNameJavaLangObjectNullGooglePlaceModelNameJavaLangStringNoName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // listener != null
                boolean listenerJavaLangObjectNull = false;
                // listener
                com.example.letmebeyourchef.maps.NearLocationInterface listener = mListener;
                // googlePlaceModel
                com.example.letmebeyourchef.model.GooglePlaceModel googlePlaceModel = mGooglePlaceModel;



                listenerJavaLangObjectNull = (listener) != (null);
                if (listenerJavaLangObjectNull) {



                    listener.onSaveClick(googlePlaceModel);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // listener != null
                boolean listenerJavaLangObjectNull = false;
                // listener
                com.example.letmebeyourchef.maps.NearLocationInterface listener = mListener;
                // googlePlaceModel
                com.example.letmebeyourchef.model.GooglePlaceModel googlePlaceModel = mGooglePlaceModel;



                listenerJavaLangObjectNull = (listener) != (null);
                if (listenerJavaLangObjectNull) {



                    listener.onDirectionClick(googlePlaceModel);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): googlePlaceModel
        flag 1 (0x2L): listener
        flag 2 (0x3L): null
        flag 3 (0x4L): googlePlaceModel.userRatingsTotal != null ? (("(") + (String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal)))) + (")") : ""
        flag 4 (0x5L): googlePlaceModel.userRatingsTotal != null ? (("(") + (String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.userRatingsTotal)))) + (")") : ""
        flag 5 (0x6L): googlePlaceModel.vicinity != null ? googlePlaceModel.vicinity : "No Address"
        flag 6 (0x7L): googlePlaceModel.vicinity != null ? googlePlaceModel.vicinity : "No Address"
        flag 7 (0x8L): googlePlaceModel.name != null ? googlePlaceModel.name : "No Name"
        flag 8 (0x9L): googlePlaceModel.name != null ? googlePlaceModel.name : "No Name"
        flag 9 (0xaL): googlePlaceModel.rating != null ? String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.rating)) : "New"
        flag 10 (0xbL): googlePlaceModel.rating != null ? String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.rating)) : "New"
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true ? @android:drawable/ic_bookmarked : @android:drawable/ic_bookmark
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(googlePlaceModel.saved) == true ? @android:drawable/ic_bookmarked : @android:drawable/ic_bookmark
    flag mapping end*/
    //end
}