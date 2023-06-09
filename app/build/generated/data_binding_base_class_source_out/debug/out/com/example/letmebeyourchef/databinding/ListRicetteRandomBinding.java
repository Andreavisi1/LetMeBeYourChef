// Generated by view binder compiler. Do not edit!
package com.example.letmebeyourchef.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.letmebeyourchef.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListRicetteRandomBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imageViewFood;

  @NonNull
  public final CardView randomListContainer;

  @NonNull
  public final TextView textViewLikes;

  @NonNull
  public final TextView textViewPorzioni;

  @NonNull
  public final TextView textViewTempo;

  @NonNull
  public final TextView textViewTitle;

  private ListRicetteRandomBinding(@NonNull CardView rootView, @NonNull ImageView imageViewFood,
      @NonNull CardView randomListContainer, @NonNull TextView textViewLikes,
      @NonNull TextView textViewPorzioni, @NonNull TextView textViewTempo,
      @NonNull TextView textViewTitle) {
    this.rootView = rootView;
    this.imageViewFood = imageViewFood;
    this.randomListContainer = randomListContainer;
    this.textViewLikes = textViewLikes;
    this.textViewPorzioni = textViewPorzioni;
    this.textViewTempo = textViewTempo;
    this.textViewTitle = textViewTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListRicetteRandomBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListRicetteRandomBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_ricette_random, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListRicetteRandomBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView_food;
      ImageView imageViewFood = ViewBindings.findChildViewById(rootView, id);
      if (imageViewFood == null) {
        break missingId;
      }

      CardView randomListContainer = (CardView) rootView;

      id = R.id.textView_likes;
      TextView textViewLikes = ViewBindings.findChildViewById(rootView, id);
      if (textViewLikes == null) {
        break missingId;
      }

      id = R.id.textView_porzioni;
      TextView textViewPorzioni = ViewBindings.findChildViewById(rootView, id);
      if (textViewPorzioni == null) {
        break missingId;
      }

      id = R.id.textView_tempo;
      TextView textViewTempo = ViewBindings.findChildViewById(rootView, id);
      if (textViewTempo == null) {
        break missingId;
      }

      id = R.id.textView_title;
      TextView textViewTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewTitle == null) {
        break missingId;
      }

      return new ListRicetteRandomBinding((CardView) rootView, imageViewFood, randomListContainer,
          textViewLikes, textViewPorzioni, textViewTempo, textViewTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
