package com.example.letmebeyourchef;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.letmebeyourchef.databinding.ActivityAggiungiBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityAggiungiEsercizioBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityAggiungiIngredienteBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityConosciamociBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityEsercizioBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityHomeBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityInizioBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityMainBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityPastoBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityProdottoBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityProfiloBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityRegisterBindingImpl;
import com.example.letmebeyourchef.databinding.ActivityScannerBindingImpl;
import com.example.letmebeyourchef.databinding.ActivitySplashScreenBindingImpl;
import com.example.letmebeyourchef.databinding.AddDeleteLayoutEserciziBindingImpl;
import com.example.letmebeyourchef.databinding.DelModSelezionatiLayoutBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentDatiPersonaliBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentDieteBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentDispensaBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentFunzioniBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentHomepage22BindingImpl;
import com.example.letmebeyourchef.databinding.FragmentHomepageBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentIngredientiPersonalizzatiBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentIngredientiPreferitiBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentIntolleranze22BindingImpl;
import com.example.letmebeyourchef.databinding.FragmentIntolleranzeBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentPersonalizzatiBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentPreferitiBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRicercaBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRicercaIngredienteBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentRicettePreferiteBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentSessoBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentStatisticheBindingImpl;
import com.example.letmebeyourchef.databinding.FragmentStorageBindingImpl;
import com.example.letmebeyourchef.databinding.ItemIngredienteBindingImpl;
import com.example.letmebeyourchef.databinding.LayoutItemEsercizioPrefBindingImpl;
import com.example.letmebeyourchef.databinding.LayoutItemEsercizioSelezionatoBindingImpl;
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

  private static final int LAYOUT_ACTIVITYAGGIUNGIESERCIZIO = 2;

  private static final int LAYOUT_ACTIVITYAGGIUNGIINGREDIENTE = 3;

  private static final int LAYOUT_ACTIVITYCONOSCIAMOCI = 4;

  private static final int LAYOUT_ACTIVITYESERCIZIO = 5;

  private static final int LAYOUT_ACTIVITYHOME = 6;

  private static final int LAYOUT_ACTIVITYINIZIO = 7;

  private static final int LAYOUT_ACTIVITYMAIN = 8;

  private static final int LAYOUT_ACTIVITYPASTO = 9;

  private static final int LAYOUT_ACTIVITYPRODOTTO = 10;

  private static final int LAYOUT_ACTIVITYPROFILO = 11;

  private static final int LAYOUT_ACTIVITYREGISTER = 12;

  private static final int LAYOUT_ACTIVITYSCANNER = 13;

  private static final int LAYOUT_ACTIVITYSPLASHSCREEN = 14;

  private static final int LAYOUT_ADDDELETELAYOUTESERCIZI = 15;

  private static final int LAYOUT_DELMODSELEZIONATILAYOUT = 16;

  private static final int LAYOUT_FRAGMENTDATIPERSONALI = 17;

  private static final int LAYOUT_FRAGMENTDIETE = 18;

  private static final int LAYOUT_FRAGMENTDISPENSA = 19;

  private static final int LAYOUT_FRAGMENTFUNZIONI = 20;

  private static final int LAYOUT_FRAGMENTHOMEPAGE = 21;

  private static final int LAYOUT_FRAGMENTHOMEPAGE22 = 22;

  private static final int LAYOUT_FRAGMENTINGREDIENTIPERSONALIZZATI = 23;

  private static final int LAYOUT_FRAGMENTINGREDIENTIPREFERITI = 24;

  private static final int LAYOUT_FRAGMENTINTOLLERANZE = 25;

  private static final int LAYOUT_FRAGMENTINTOLLERANZE22 = 26;

  private static final int LAYOUT_FRAGMENTPERSONALIZZATI = 27;

  private static final int LAYOUT_FRAGMENTPREFERITI = 28;

  private static final int LAYOUT_FRAGMENTRICERCA = 29;

  private static final int LAYOUT_FRAGMENTRICERCAINGREDIENTE = 30;

  private static final int LAYOUT_FRAGMENTRICETTEPREFERITE = 31;

  private static final int LAYOUT_FRAGMENTSESSO = 32;

  private static final int LAYOUT_FRAGMENTSTATISTICHE = 33;

  private static final int LAYOUT_FRAGMENTSTORAGE = 34;

  private static final int LAYOUT_ITEMINGREDIENTE = 35;

  private static final int LAYOUT_LAYOUTITEMESERCIZIOPREF = 36;

  private static final int LAYOUT_LAYOUTITEMESERCIZIOSELEZIONATO = 37;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(37);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_aggiungi, LAYOUT_ACTIVITYAGGIUNGI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_aggiungi_esercizio, LAYOUT_ACTIVITYAGGIUNGIESERCIZIO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_aggiungi_ingrediente, LAYOUT_ACTIVITYAGGIUNGIINGREDIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_conosciamoci, LAYOUT_ACTIVITYCONOSCIAMOCI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_esercizio, LAYOUT_ACTIVITYESERCIZIO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_home, LAYOUT_ACTIVITYHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_inizio, LAYOUT_ACTIVITYINIZIO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_pasto, LAYOUT_ACTIVITYPASTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_prodotto, LAYOUT_ACTIVITYPRODOTTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_profilo, LAYOUT_ACTIVITYPROFILO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_register, LAYOUT_ACTIVITYREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_scanner, LAYOUT_ACTIVITYSCANNER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.activity_splash_screen, LAYOUT_ACTIVITYSPLASHSCREEN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.add_delete_layout_esercizi, LAYOUT_ADDDELETELAYOUTESERCIZI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.del_mod_selezionati_layout, LAYOUT_DELMODSELEZIONATILAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_dati_personali, LAYOUT_FRAGMENTDATIPERSONALI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_diete, LAYOUT_FRAGMENTDIETE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_dispensa, LAYOUT_FRAGMENTDISPENSA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_funzioni, LAYOUT_FRAGMENTFUNZIONI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_homepage, LAYOUT_FRAGMENTHOMEPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_homepage22, LAYOUT_FRAGMENTHOMEPAGE22);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ingredienti_personalizzati, LAYOUT_FRAGMENTINGREDIENTIPERSONALIZZATI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ingredienti_preferiti, LAYOUT_FRAGMENTINGREDIENTIPREFERITI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_intolleranze, LAYOUT_FRAGMENTINTOLLERANZE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_intolleranze22, LAYOUT_FRAGMENTINTOLLERANZE22);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_personalizzati, LAYOUT_FRAGMENTPERSONALIZZATI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_preferiti, LAYOUT_FRAGMENTPREFERITI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ricerca, LAYOUT_FRAGMENTRICERCA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ricerca_ingrediente, LAYOUT_FRAGMENTRICERCAINGREDIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_ricette_preferite, LAYOUT_FRAGMENTRICETTEPREFERITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_sesso, LAYOUT_FRAGMENTSESSO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_statistiche, LAYOUT_FRAGMENTSTATISTICHE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.fragment_storage, LAYOUT_FRAGMENTSTORAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.item_ingrediente, LAYOUT_ITEMINGREDIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.layout_item_esercizio_pref, LAYOUT_LAYOUTITEMESERCIZIOPREF);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.letmebeyourchef.R.layout.layout_item_esercizio_selezionato, LAYOUT_LAYOUTITEMESERCIZIOSELEZIONATO);
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
        case  LAYOUT_ACTIVITYAGGIUNGIESERCIZIO: {
          if ("layout/activity_aggiungi_esercizio_0".equals(tag)) {
            return new ActivityAggiungiEsercizioBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_aggiungi_esercizio is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYAGGIUNGIINGREDIENTE: {
          if ("layout/activity_aggiungi_ingrediente_0".equals(tag)) {
            return new ActivityAggiungiIngredienteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_aggiungi_ingrediente is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCONOSCIAMOCI: {
          if ("layout/activity_conosciamoci_0".equals(tag)) {
            return new ActivityConosciamociBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_conosciamoci is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYESERCIZIO: {
          if ("layout/activity_esercizio_0".equals(tag)) {
            return new ActivityEsercizioBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_esercizio is invalid. Received: " + tag);
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
        case  LAYOUT_ACTIVITYPASTO: {
          if ("layout/activity_pasto_0".equals(tag)) {
            return new ActivityPastoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_pasto is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPRODOTTO: {
          if ("layout/activity_prodotto_0".equals(tag)) {
            return new ActivityProdottoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_prodotto is invalid. Received: " + tag);
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
        case  LAYOUT_ADDDELETELAYOUTESERCIZI: {
          if ("layout/add_delete_layout_esercizi_0".equals(tag)) {
            return new AddDeleteLayoutEserciziBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for add_delete_layout_esercizi is invalid. Received: " + tag);
        }
        case  LAYOUT_DELMODSELEZIONATILAYOUT: {
          if ("layout/del_mod_selezionati_layout_0".equals(tag)) {
            return new DelModSelezionatiLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for del_mod_selezionati_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDATIPERSONALI: {
          if ("layout/fragment_dati_personali_0".equals(tag)) {
            return new FragmentDatiPersonaliBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_dati_personali is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDIETE: {
          if ("layout/fragment_diete_0".equals(tag)) {
            return new FragmentDieteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_diete is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDISPENSA: {
          if ("layout/fragment_dispensa_0".equals(tag)) {
            return new FragmentDispensaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_dispensa is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFUNZIONI: {
          if ("layout/fragment_funzioni_0".equals(tag)) {
            return new FragmentFunzioniBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_funzioni is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTINGREDIENTIPERSONALIZZATI: {
          if ("layout/fragment_ingredienti_personalizzati_0".equals(tag)) {
            return new FragmentIngredientiPersonalizzatiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_ingredienti_personalizzati is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTINTOLLERANZE22: {
          if ("layout/fragment_intolleranze22_0".equals(tag)) {
            return new FragmentIntolleranze22BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_intolleranze22 is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSONALIZZATI: {
          if ("layout/fragment_personalizzati_0".equals(tag)) {
            return new FragmentPersonalizzatiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_personalizzati is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPREFERITI: {
          if ("layout/fragment_preferiti_0".equals(tag)) {
            return new FragmentPreferitiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_preferiti is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTSESSO: {
          if ("layout/fragment_sesso_0".equals(tag)) {
            return new FragmentSessoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sesso is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSTATISTICHE: {
          if ("layout/fragment_statistiche_0".equals(tag)) {
            return new FragmentStatisticheBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_statistiche is invalid. Received: " + tag);
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
        case  LAYOUT_LAYOUTITEMESERCIZIOPREF: {
          if ("layout/layout_item_esercizio_pref_0".equals(tag)) {
            return new LayoutItemEsercizioPrefBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for layout_item_esercizio_pref is invalid. Received: " + tag);
        }
        case  LAYOUT_LAYOUTITEMESERCIZIOSELEZIONATO: {
          if ("layout/layout_item_esercizio_selezionato_0".equals(tag)) {
            return new LayoutItemEsercizioSelezionatoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for layout_item_esercizio_selezionato is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "ingrediente");
      sKeys.put(2, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(37);

    static {
      sKeys.put("layout/activity_aggiungi_0", com.example.letmebeyourchef.R.layout.activity_aggiungi);
      sKeys.put("layout/activity_aggiungi_esercizio_0", com.example.letmebeyourchef.R.layout.activity_aggiungi_esercizio);
      sKeys.put("layout/activity_aggiungi_ingrediente_0", com.example.letmebeyourchef.R.layout.activity_aggiungi_ingrediente);
      sKeys.put("layout/activity_conosciamoci_0", com.example.letmebeyourchef.R.layout.activity_conosciamoci);
      sKeys.put("layout/activity_esercizio_0", com.example.letmebeyourchef.R.layout.activity_esercizio);
      sKeys.put("layout/activity_home_0", com.example.letmebeyourchef.R.layout.activity_home);
      sKeys.put("layout/activity_inizio_0", com.example.letmebeyourchef.R.layout.activity_inizio);
      sKeys.put("layout/activity_main_0", com.example.letmebeyourchef.R.layout.activity_main);
      sKeys.put("layout/activity_pasto_0", com.example.letmebeyourchef.R.layout.activity_pasto);
      sKeys.put("layout/activity_prodotto_0", com.example.letmebeyourchef.R.layout.activity_prodotto);
      sKeys.put("layout/activity_profilo_0", com.example.letmebeyourchef.R.layout.activity_profilo);
      sKeys.put("layout/activity_register_0", com.example.letmebeyourchef.R.layout.activity_register);
      sKeys.put("layout/activity_scanner_0", com.example.letmebeyourchef.R.layout.activity_scanner);
      sKeys.put("layout/activity_splash_screen_0", com.example.letmebeyourchef.R.layout.activity_splash_screen);
      sKeys.put("layout/add_delete_layout_esercizi_0", com.example.letmebeyourchef.R.layout.add_delete_layout_esercizi);
      sKeys.put("layout/del_mod_selezionati_layout_0", com.example.letmebeyourchef.R.layout.del_mod_selezionati_layout);
      sKeys.put("layout/fragment_dati_personali_0", com.example.letmebeyourchef.R.layout.fragment_dati_personali);
      sKeys.put("layout/fragment_diete_0", com.example.letmebeyourchef.R.layout.fragment_diete);
      sKeys.put("layout/fragment_dispensa_0", com.example.letmebeyourchef.R.layout.fragment_dispensa);
      sKeys.put("layout/fragment_funzioni_0", com.example.letmebeyourchef.R.layout.fragment_funzioni);
      sKeys.put("layout/fragment_homepage_0", com.example.letmebeyourchef.R.layout.fragment_homepage);
      sKeys.put("layout/fragment_homepage22_0", com.example.letmebeyourchef.R.layout.fragment_homepage22);
      sKeys.put("layout/fragment_ingredienti_personalizzati_0", com.example.letmebeyourchef.R.layout.fragment_ingredienti_personalizzati);
      sKeys.put("layout/fragment_ingredienti_preferiti_0", com.example.letmebeyourchef.R.layout.fragment_ingredienti_preferiti);
      sKeys.put("layout/fragment_intolleranze_0", com.example.letmebeyourchef.R.layout.fragment_intolleranze);
      sKeys.put("layout/fragment_intolleranze22_0", com.example.letmebeyourchef.R.layout.fragment_intolleranze22);
      sKeys.put("layout/fragment_personalizzati_0", com.example.letmebeyourchef.R.layout.fragment_personalizzati);
      sKeys.put("layout/fragment_preferiti_0", com.example.letmebeyourchef.R.layout.fragment_preferiti);
      sKeys.put("layout/fragment_ricerca_0", com.example.letmebeyourchef.R.layout.fragment_ricerca);
      sKeys.put("layout/fragment_ricerca_ingrediente_0", com.example.letmebeyourchef.R.layout.fragment_ricerca_ingrediente);
      sKeys.put("layout/fragment_ricette_preferite_0", com.example.letmebeyourchef.R.layout.fragment_ricette_preferite);
      sKeys.put("layout/fragment_sesso_0", com.example.letmebeyourchef.R.layout.fragment_sesso);
      sKeys.put("layout/fragment_statistiche_0", com.example.letmebeyourchef.R.layout.fragment_statistiche);
      sKeys.put("layout/fragment_storage_0", com.example.letmebeyourchef.R.layout.fragment_storage);
      sKeys.put("layout/item_ingrediente_0", com.example.letmebeyourchef.R.layout.item_ingrediente);
      sKeys.put("layout/layout_item_esercizio_pref_0", com.example.letmebeyourchef.R.layout.layout_item_esercizio_pref);
      sKeys.put("layout/layout_item_esercizio_selezionato_0", com.example.letmebeyourchef.R.layout.layout_item_esercizio_selezionato);
    }
  }
}
