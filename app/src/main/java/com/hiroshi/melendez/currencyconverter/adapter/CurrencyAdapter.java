package com.hiroshi.melendez.currencyconverter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hiroshi.melendez.currencyconverter.R;
import com.hiroshi.melendez.currencyconverter.model.Currency;

import java.util.List;

/**
 * Created by melendezhiroshi on 3/8/18.
 */

public class CurrencyAdapter
        extends ArrayAdapter<Currency>
        implements AdapterView.OnItemClickListener {

    private CurrencySelectableListener currencySelectableListener;

    public CurrencyAdapter(Context context, List<Currency> currencyList) {
        super(context, 0, currencyList);
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        Currency currency = getItem(position);
        if (convertView == null){
            //
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_currency, parent, false);
        }

        TextView currencyNameTextView = convertView.findViewById(R.id.currency_name);
        ImageView currencyFlag = convertView.findViewById(R.id.currency_flag);

        if (currency != null) {
            currencyNameTextView.setText(currency.toString());
            currencyFlag.setImageResource(currency.getFlagID());
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Currency currency = getItem(i);
        if (this.currencySelectableListener != null){
            this.currencySelectableListener.selectedCurrency(currency);
        }
    }

    public void setCurrencySelectableListener(CurrencySelectableListener currencySelectableListener) {
        this.currencySelectableListener = currencySelectableListener;
    }

    public interface CurrencySelectableListener {
        void selectedCurrency(Currency currency);
    }
}