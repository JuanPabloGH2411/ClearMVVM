package com.example.cleanmvvm.presentation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanmvvm.R
import com.example.cleanmvvm.domain.model.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class ReposAdapter (val repoList:List<Repo>):
    RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepoViewHolder(inflater.inflate(R.layout.item_repo,parent,false))
    }

    override fun getItemCount(): Int {
       return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
      holder.txtname.text = repoList[position].name
        holder.txtdescription.text = repoList[position].description

    }


    class RepoViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val txtname =view.findViewById<TextView>(R.id.txtname)
        val txtdescription = view.findViewById<TextView>(R.id.txtdescription)
        }
}

