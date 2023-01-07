package vishnu.teja.dictionaryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vishnu.teja.dictionaryapp.R;
import vishnu.teja.dictionaryapp.ViewHolders.DefinitionViewHolder;
import vishnu.teja.dictionaryapp.models.Definations;


import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {


    private Context context;
    private List<Definations> definationsList;

    public DefinitionAdapter(Context context, List<Definations> definationsList) {
        this.context = context;
        this.definationsList = definationsList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new DefinitionViewHolder( LayoutInflater.from( context ).inflate( R.layout.definitions_list_items,parent,false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {

        holder.textView_definition.setText( "Definition: "+definationsList.get(position).getDefinition() );
        holder.textView_example.setText( "Example: "+definationsList.get( position ).getExample() );

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append( definationsList.get( position ).getSynonyms() );
        antonyms.append( definationsList.get( position ).getAntonyms() );

        holder.textView_ant.setText( synonyms );
        holder.textView_syn.setText( antonyms );

        holder.textView_syn.setSelected( true );
        holder.textView_ant.setSelected( true );
    }

    @Override
    public int getItemCount() {
        return definationsList.size();
    }
}
