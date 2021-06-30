package com.example.enterprises.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enterprises.R
import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.enterprise.EnterpriseResponse
import com.example.enterprises.extensions.downloadImage

class EnterpriseListAdapter(
    private val enterprises: List<EnterpriseResponse?>,
    private val context: Context
) : RecyclerView.Adapter<EnterpriseListAdapter.EnterpriseListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnterpriseListViewHolder {
        return EnterpriseListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_enterprise,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EnterpriseListViewHolder, position: Int) {
        holder.bind(enterprises[position], context)
    }

    override fun getItemCount(): Int {
        return enterprises.size
    }

    class EnterpriseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemEnterpriseImageView: ImageView =
            itemView.findViewById(R.id.itemEnterpriseImageView)
        private val itemEnterpriseNameTextView: TextView =
            itemView.findViewById(R.id.itemEnterpriseNameTextView)
        private val itemEnterpriseTypeTextView: TextView =
            itemView.findViewById(R.id.itemEnterpriseTypeTextView)
        private val itemEnterpriseCountryTextView: TextView =
            itemView.findViewById(R.id.itemEnterpriseCountryTextView)

        fun bind(enterpriseResponse: EnterpriseResponse?, context: Context) {
            itemEnterpriseCountryTextView.text = enterpriseResponse?.country
            itemEnterpriseTypeTextView.text =
                enterpriseResponse?.enterpriseTypeResponse?.enterpriseTypeName
            itemEnterpriseNameTextView.text = enterpriseResponse?.enterpriseName
            itemEnterpriseImageView.downloadImage(
                Constants.BASE_IMAGE_URL + enterpriseResponse?.photo,
                context
            )

        }
    }
}