package com.pokedeck.imie.pokedeck.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pokedeck.imie.pokedeck.R;
import com.pokedeck.imie.pokedeck.entity.Pokemon;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Alexandre on 10/06/2016.
 */
public class PokedeckAdapter extends ArrayAdapter<Pokemon> {

    Context context;

    public PokedeckAdapter(Context context, List<Pokemon> listPokemon) {
        super(context, -1, listPokemon);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_pokedeck, null);
        } else {
            view = convertView;
        }

        Pokemon pokemon = getItem(position);

        // Current pokemon's reference is store in the current view
        view.setTag(pokemon);

        /*TextView nickname = (TextView) view.findViewById(R.id.listitem_pokedeck_nickname);
        TextView pv = (TextView) view.findViewById(R.id.listitem_pokedeck_pv);

        nickname.setText(pokemon.getNickname());
        pv.setText(pokemon.getPv().toString());*/

        ImageView pokemonImg = (ImageView) view.findViewById(R.id.listitem_pokedeck_image);
        try {
            Class res = R.drawable.class;
            Field field = res.getField(pokemon.getNickname().toLowerCase());
            int drawableId = field.getInt(null);
            pokemonImg.setImageResource(drawableId);
        }
        catch (Exception e) {
            Log.e("Image source error", "Failed to get drawable id.", e);
        };
        return view;
    }
}
