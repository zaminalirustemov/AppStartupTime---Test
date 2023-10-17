package com.lahza.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.lahza.todo.databinding.CategoryItemBinding
import com.lahza.todo.models.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoryList = ArrayList<Category>()

    inner class CategoryViewHolder(var binding: CategoryItemBinding) : ViewHolder(binding.root)

    fun setCategories(categoryList: ArrayList<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoryList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)

        holder.binding.tvCategoryName.text = categoryList[position].strCategory
    }
}