package com.example.letmebeyourchef;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.letmebeyourchef.databinding.ActivityAggiungiBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityAggiungiIngredienteBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityChefBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityConosciamociBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityHomeBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityInizioBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityMainBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityProfiloBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityRegisterBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityScannerBindingImpl;
import com.example.letmebeyourchef.databinding.ActivitySplashScreenBindingImpl;
import com.example.letmebeyourchef.databinding.DelModSelezionatiLayoutBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentCartBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentDatiPersonaliBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentHomepage22BindingImpl;
import com.example.letmebeyourchef.databinding.FragmentHomepageBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentIngredientiPreferitiBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentIntolleranzeBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentOptimizeRouteBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRatePlaceBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRicercaBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRicercaIngredienteBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRicettePreferiteBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentSavedPlaceBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentSessoBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentStorageBindingImpl;
import com.example.letmebeyourchef.databinding.ItemIngredienteBindingImpl;
import com.example.letmebeyourchef.databinding.PlaceItemLayoutBindingImpl;
import com.example.letmebeyourchef.databinding.SavedItemLayoutBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYAGGIUNGI = 1;

  private static final int LAYOUT_ACTIVITYAGGIUNGIINGREDIENTE = 2;

  private static final int LAYOUT_ACTIVITYCHEF = 3;

  private static final int LAYOUT_ACTIVITYCONOSCIAMOCI = 4;

  private static final int LAYOUT_ACTIVITYHOME = 5;

  private static final int LAYOUT_ACTIVITYINIZIO = 6;

  private static final int LAYOUT_ACTIVITYMAIN = 7;

  private static final int LAYOUT_ACTIVITYPROFILO = 8;

  private static final int LAYOUT_ACTIVITYREGISTER = 9;

  private static final int LAYOUT_ACTIVITYSCANNER = 10;

  private static final int LAYOUT_ACTIVITYSPLASHSCREEN = 11;

  private static final int LAYOUT_DELMODSELEZIONATILAYOUT = 12;

  private static final int LAYOUT_FRAGMENTCART = 13;

  private static final int LAYOUT_FRAGMENTDATIPERSONALI = 14;

  private static final int LAYOUT_FRAGMENTHOMEPAGE = 15;

  private static final int LAYOUT_FRAGMENTHOMEPAGE22 = 16;

  private static final int LAYOUT_FRAGMENTINGREDIENTIPREFERITI = 17;

  private static final int LAYOUT_FRAGMENTINTOLLERANZE = 18;

  private static final int LAYOUT_FRAGMENTOPTIMIZEROUTE = 19;

  private static final int LAYOUT_FRAGMENTRATEPLACE = 20;

  private static final int LAYOUT_FRAGMENTRICERCA = 21;

  private static final int LAYOUT_FRAGMENTRICERCAINGREDIENTE = 22;

  private static final int LAYOUT_FRAGMENTRICETTEPREFERITE = 23;

  private static final int LAYOUT_FRAGMENTSAVEDPLACE = 24;

  private static final int LAYOUT_FRAGMENTSESSO = 25;

  private static final int LAYOUT_FRAGMENTSTORAGE = 26;

  private static final int LAYOUT_ITEMINGREDIENTE = 27;

  private static final int LAYOUT_PLACEITEMLAYOUT = 28;

  private static final int LAYOUT_SAVEDITEMLAYOUT = 29;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(29);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_aggiungi, LAYOUT_ACTIVITYAGGIUNGI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_aggiungi_ingrediente, LAYOUT_ACTIVITYAGGIUNGIINGREDIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_chef, LAYOUT_ACTIVITYCHEF);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_conosciamoci, LAYOUT_ACTIVITYCONOSCIAMOCI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_home, LAYOUT_ACTIVITYHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_inizio, LAYOUT_ACTIVITYINIZIO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_profilo, LAYOUT_ACTIVITYPROFILO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_register, LAYOUT_ACTIVITYREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_scanner, LAYOUT_ACTIVITYSCANNER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_splash_screen, LAYOUT_ACTIVITYSPLASHSCREEN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.del_mod_selezionati_layout, LAYOUT_DELMODSELEZIONATILAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_cart, LAYOUT_FRAGMENTCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_dati_personali, LAYOUT_FRAGMENTDATIPERSONALI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_homepage, LAYOUT_FRAGMENTHOMEPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_homepage22, LAYOUT_FRAGMENTHOMEPAGE22);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ingredienti_preferiti, LAYOUT_FRAGMENTINGREDIENTIPREFERITI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_intolleranze, LAYOUT_FRAGMENTINTOLLERANZE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_optimize_route, LAYOUT_FRAGMENTOPTIMIZEROUTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_rate_place, LAYOUT_FRAGMENTRATEPLACE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ricerca, LAYOUT_FRAGMENTRICERCA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ricerca_ingrediente, LAYOUT_FRAGMENTRICERCAINGREDIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ricette_preferite, LAYOUT_FRAGMENTRICETTEPREFERITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_saved_place, LAYOUT_FRAGMENTSAVEDPLACE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_sesso, LAYOUT_FRAGMENTSESSO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_storage, LAYOUT_FRAGMENTSTORAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.item_ingrediente, LAYOUT_ITEMINGREDIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.place_item_layout, LAYOUT_PLACEITEMLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.saved_item_layout, LAYOUT_SAVEDITEMLAYOUT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYAGGIUNGI: {
          if ("layout/activity_aggiungi_0".equals(tag)) {
            return new ActivityAggiungiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_aggiungi is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYAGGIUNGIINGREDIENTE: {
          if ("layout/activity_aggiungi_ingrediente_0".equals(tag)) {
            return new ActivityAggiungiIngredienteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_aggiungi_ingrediente is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCHEF: {
          if ("layout/activity_chef_0".equals(tag)) {
            return new ActivityChefBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_chef is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCONOSCIAMOCI: {
          if ("layout/activity_conosciamoci_0".equals(tag)) {
            return new ActivityConosciamociBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_conosciamoci is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYHOME: {
          if ("layout/activity_home_0".equals(tag)) {
            return new ActivityHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_home is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYINIZIO: {
          if ("layout/activity_inizio_0".equals(tag)) {
            return new ActivityInizioBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_inizio is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPROFILO: {
          if ("layout/activity_profilo_0".equals(tag)) {
            return new ActivityProfiloBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_profilo is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREGISTER: {
          if ("layout/activity_register_0".equals(tag)) {
            return new ActivityRegisterBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_register is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSCANNER: {
          if ("layout/activity_scanner_0".equals(tag)) {
            return new ActivityScannerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_scanner is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPLASHSCREEN: {
          if ("layout/activity_splash_screen_0".equals(tag)) {
            return new ActivitySplashScreenBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_splash_screen is invalid. Received: " + tag);
        }
        case  LAYOUT_DELMODSELEZIONATILAYOUT: {
          if ("layout/del_mod_selezionati_layout_0".equals(tag)) {
            return new DelModSelezionatiLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for del_mod_selezionati_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCART: {
          if ("layout/fragment_cart_0".equals(tag)) {
            return new FragmentCartBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_cart is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDATIPERSONALI: {
          if ("layout/fragment_dati_personali_0".equals(tag)) {
            return new FragmentDatiPersonaliBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_dati_personali is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOMEPAGE: {
          if ("layout/fragment_homepage_0".equals(tag)) {
            return new FragmentHomepageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_homepage is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOMEPAGE22: {
          if ("layout/fragment_homepage22_0".equals(tag)) {
            return new FragmentHomepage22BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_homepage22 is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTINGREDIENTIPREFERITI: {
          if ("layout/fragment_ingredienti_preferiti_0".equals(tag)) {
            return new FragmentIngredientiPreferitiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_ingredienti_preferiti is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTINTOLLERANZE: {
          if ("layout/fragment_intolleranze_0".equals(tag)) {
            return new FragmentIntolleranzeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_intolleranze is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTOPTIMIZEROUTE: {
          if ("layout/fragment_optimize_route_0".equals(tag)) {
            return new FragmentOptimizeRouteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_optimize_route is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRATEPLACE: {
          if ("layout/fragment_rate_place_0".equals(tag)) {
            return new FragmentRatePlaceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_rate_place is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRICERCA: {
          if ("layout/fragment_ricerca_0".equals(tag)) {
            return new FragmentRicercaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_ricerca is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRICERCAINGREDIENTE: {
          if ("layout/fragment_ricerca_ingrediente_0".equals(tag)) {
            return new FragmentRicercaIngredienteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_ricerca_ingrediente is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRICETTEPREFERITE: {
          if ("layout/fragment_ricette_preferite_0".equals(tag)) {
            return new FragmentRicettePreferiteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_ricette_preferite is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSAVEDPLACE: {
          if ("layout/fragment_saved_place_0".equals(tag)) {
            return new FragmentSavedPlaceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_saved_place is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSESSO: {
          if ("layout/fragment_sesso_0".equals(tag)) {
            return new FragmentSessoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sesso is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSTORAGE: {
          if ("layout/fragment_storage_0".equals(tag)) {
            return new FragmentStorageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_storage is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMINGREDIENTE: {
          if ("layout/item_ingrediente_0".equals(tag)) {
            return new ItemIngredienteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_ingrediente is invalid. Received: " + tag);
        }
        case  LAYOUT_PLACEITEMLAYOUT: {
          if ("layout/place_item_layout_0".equals(tag)) {
            return new PlaceItemLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for place_item_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_SAVEDITEMLAYOUT: {
          if ("layout/saved_item_layout_0".equals(tag)) {
            return new SavedItemLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for saved_item_layout is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(6);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "googlePlaceModel");
      sKeys.put(2, "ingrediente");
      sKeys.put(3, "listener");
      sKeys.put(4, "savedPlaceModel");
      sKeys.put(5, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(29);

    static {
      sKeys.put("layout/activity_aggiungi_0", com.example.letmebeyourchef.R.layout.activity_aggiungi);
      sKeys.put("layout/activity_aggiungi_ingrediente_0", com.example.letmebeyourchef.R.layout.activity_aggiungi_ingrediente);
      sKeys.put("layout/activity_chef_0", com.example.letmebeyourchef.R.layout.activity_chef);
      sKeys.put("layout/activity_conosciamoci_0", com.example.letmebeyourchef.R.layout.activity_conosciamoci);
      sKeys.put("layout/activity_home_0", com.example.letmebeyourchef.R.layout.activity_home);
      sKeys.put("layout/activity_inizio_0", com.example.letmebeyourchef.R.layout.activity_inizio);
      sKeys.put("layout/activity_main_0", com.example.letmebeyourchef.R.layout.activity_main);
      sKeys.put("layout/activity_profilo_0", com.example.letmebeyourchef.R.layout.activity_profilo);
      sKeys.put("layout/activity_register_0", com.example.letmebeyourchef.R.layout.activity_register);
      sKeys.put("layout/activity_scanner_0", com.example.letmebeyourchef.R.layout.activity_scanner);
      sKeys.put("layout/activity_splash_screen_0", com.example.letmebeyourchef.R.layout.activity_splash_screen);
      sKeys.put("layout/del_mod_selezionati_layout_0", com.example.letmebeyourchef.R.layout.del_mod_selezionati_layout);
      sKeys.put("layout/fragment_cart_0", com.example.letmebeyourchef.R.layout.fragment_cart);
      sKeys.put("layout/fragment_dati_personali_0", com.example.letmebeyourchef.R.layout.fragment_dati_personali);
      sKeys.put("layout/fragment_homepage_0", com.example.letmebeyourchef.R.layout.fragment_homepage);
      sKeys.put("layout/fragment_homepage22_0", com.example.letmebeyourchef.R.layout.fragment_homepage22);
      sKeys.put("layout/fragment_ingredienti_preferiti_0", com.example.letmebeyourchef.R.layout.fragment_ingredienti_preferiti);
      sKeys.put("layout/fragment_intolleranze_0", com.example.letmebeyourchef.R.layout.fragment_intolleranze);
      sKeys.put("layout/fragment_optimize_route_0", com.example.letmebeyourchef.R.layout.fragment_optimize_route);
      sKeys.put("layout/fragment_rate_place_0", com.example.letmebeyourchef.R.layout.fragment_rate_place);
      sKeys.put("layout/fragment_ricerca_0", com.example.letmebeyourchef.R.layout.fragment_ricerca);
      sKeys.put("layout/fragment_ricerca_ingrediente_0", com.example.letmebeyourchef.R.layout.fragment_ricerca_ingrediente);
      sKeys.put("layout/fragment_ricette_preferite_0", com.example.letmebeyourchef.R.layout.fragment_ricette_preferite);
      sKeys.put("layout/fragment_saved_place_0", com.example.letmebeyourchef.R.layout.fragment_saved_place);
      sKeys.put("layout/fragment_sesso_0", com.example.letmebeyourchef.R.layout.fragment_sesso);
      sKeys.put("layout/fragment_storage_0", com.example.letmebeyourchef.R.layout.fragment_storage);
      sKeys.put("layout/item_ingrediente_0", com.example.letmebeyourchef.R.layout.item_ingrediente);
      sKeys.put("layout/place_item_layout_0", com.example.letmebeyourchef.R.layout.place_item_layout);
      sKeys.put("layout/saved_item_layout_0", com.example.letmebeyourchef.R.layout.saved_item_layout);
    }
  }
}
