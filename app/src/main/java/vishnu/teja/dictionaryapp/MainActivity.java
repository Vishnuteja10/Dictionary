package vishnu.teja.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import vishnu.teja.dictionaryapp.Adapters.MeaningAdapter;
import vishnu.teja.dictionaryapp.Adapters.PhoneticsAdapter;

import vishnu.teja.dictionaryapp.R;

import vishnu.teja.dictionaryapp.models.APIResponse;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView textview_word;
    RecyclerView recycler_phonetix,recycler_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        searchView = findViewById( R.id.search_view );
        textview_word = findViewById( R.id.textview_word );
        recycler_phonetix = findViewById( R.id.recycler_phonetix );
        recycler_meanings = findViewById( R.id.recycler_meanings );

        progressDialog = new ProgressDialog( this );

        progressDialog.setTitle( "Loading...." );
        progressDialog.show();
        RequestManager manager = new RequestManager( MainActivity.this);
        manager.getWordMeaning( listener,"hello" );



        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle( "Fetching Response For "+ query );
                progressDialog.show();
                RequestManager manager = new RequestManager( MainActivity.this);
                manager.getWordMeaning( listener,query );
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );

    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse == null){
                Toast.makeText(  MainActivity.this,"No Data Found ",Toast.LENGTH_SHORT ).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
             progressDialog.dismiss();
             Toast.makeText( MainActivity.this,message,Toast.LENGTH_SHORT ).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        // we will attach data to recycler view
        textview_word.setText( "word: "+apiResponse.getWord() );
        recycler_phonetix.setHasFixedSize( true );
        recycler_phonetix.setLayoutManager( new GridLayoutManager( this,1 ) );
        phoneticsAdapter = new PhoneticsAdapter( this,apiResponse.getPhonetics() );
        recycler_phonetix.setAdapter( phoneticsAdapter );

        recycler_meanings.setHasFixedSize( true );
        recycler_meanings.setLayoutManager( new GridLayoutManager( this,1 ) );
        meaningAdapter = new MeaningAdapter( this,apiResponse.getMeanings() );
        recycler_meanings.setAdapter( meaningAdapter );


    }
}