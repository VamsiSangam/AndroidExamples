package com.vamsisangam.androidexamples;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static com.vamsisangam.androidexamples.App.*;

import java.util.ArrayList;

/**
 * Created by Vamsi on 31-05-2017.
 */

public class CompanyAdapter extends ArrayAdapter<Company> {
    public Context ctx;
    public ArrayList<Company> companies;

    public CompanyAdapter(Context ctx, ArrayList<Company> companies) {
        super(ctx, R.layout.company_layout, companies);
        this.ctx = ctx;
        this.companies = companies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);

            convertView = inflater.inflate(R.layout.company_layout, parent, false);
        }

        Button btnCall = (Button) convertView.findViewById(R.id.CompanyLayoutCall);
        Button btnWebsite = (Button) convertView.findViewById(R.id.CompanyLayoutWebsite);
        TextView txtName = (TextView) convertView.findViewById(R.id.CompanyLayoutName);
        ImageView imgLogo = (ImageView) convertView.findViewById(R.id.CompanyLayoutImage);
        Resources res = ctx.getResources();
        final Company company = companies.get(position);

        txtName.setText(company.getName());

        final int resourceId = res.getIdentifier(company.getLogo(), "drawable", ctx.getPackageName());
        imgLogo.setImageResource(resourceId);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + company.getPhone()));

                try {
                    ctx.startActivity(intent);
                } catch (Exception ex) {
                    log("Exception - " + ex.getMessage());
                }
            }
        });

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(company.getUrl()));

                try {
                    ctx.startActivity(intent);
                } catch (Exception ex) {
                    log("Exception - " + ex.getMessage());
                }
            }
        });

        return convertView;
    }
}
