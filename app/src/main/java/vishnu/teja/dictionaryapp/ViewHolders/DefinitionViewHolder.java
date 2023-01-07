package vishnu.teja.dictionaryapp.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vishnu.teja.dictionaryapp.R;



public class DefinitionViewHolder extends RecyclerView.ViewHolder {

    public TextView textView_definition,textView_example,textView_syn,textView_ant;

    public DefinitionViewHolder(@NonNull  View itemView) {
        super( itemView );

        textView_definition = itemView.findViewById( R.id.textView_definition );
        textView_example = itemView.findViewById( R.id.textView_example );
        textView_syn = itemView.findViewById( R.id.textView_syn );
        textView_ant = itemView.findViewById( R.id.textView_ant );
    }
}
