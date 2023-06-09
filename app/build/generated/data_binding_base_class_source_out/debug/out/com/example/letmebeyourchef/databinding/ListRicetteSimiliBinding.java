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

public final class ListRicetteSimiliBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imageViewSimili;

  @NonNull
  public final CardView ricetteSimiliHolder;

  @NonNull
  public final TextView textViewSimiliPorzione;

  @NonNull
  public final TextView textViewSimiliTitolo;

  private ListRicetteSimiliBinding(@NonNull CardView rootView, @NonNull ImageView imageViewSimili,
      @NonNull CardView ricetteSimiliHolder, @NonNull TextView textViewSimiliPorzione,
      @NonNull TextView textViewSimiliTitolo) {
    this.rootView = rootView;
    this.imageViewSimili = imageViewSimili;
    this.ricetteSimiliHolder = ricetteSimiliHolder;
    this.textViewSimiliPorzione = textViewSimiliPorzione;
    this.textViewSimiliTitolo = textViewSimiliTitolo;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListRicetteSimiliBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListRicetteSimiliBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_ricette_simili, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListRicetteSimiliBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView_simili;
      ImageView imageViewSimili = ViewBindings.findChildViewById(rootView, id);
      if (imageViewSimili == null) {
        break missingId;
      }

      CardView ricetteSimiliHolder = (CardView) rootView;

      id = R.id.textView_simili_porzione;
      TextView textViewSimiliPorzione = ViewBindings.findChildViewById(rootView, id);
      if (textViewSimiliPorzione == null) {
        break missingId;
      }

      id = R.id.textView_simili_titolo;
      TextView textViewSimiliTitolo = ViewBindings.findChildViewById(rootView, id);
      if (textViewSimiliTitolo == null) {
        break missingId;
      }

      return new ListRicetteSimiliBinding((CardView) rootView, imageViewSimili, ricetteSimiliHolder,
          textViewSimiliPorzione, textViewSimiliTitolo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
