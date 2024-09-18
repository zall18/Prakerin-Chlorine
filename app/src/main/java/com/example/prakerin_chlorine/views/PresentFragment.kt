package com.example.prakerin_chlorine.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import com.example.prakerin_chlorine.EditPofileActivity
import com.example.prakerin_chlorine.HistoryAbsenceActivity
import com.example.prakerin_chlorine.R

class PresentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_present, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val option: ImageView = view.findViewById(R.id.option)

        option.setOnClickListener {
            popupMenu(option);
        }
    }
    private fun popupMenu(view: View) {
        var popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.option_present, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
                item ->
            when(item.itemId){
                R.id.history -> {
                    startActivity(Intent(requireContext(), HistoryAbsenceActivity::class.java))
                    true
                }
                else -> false
            }
        }

        popupMenu.setOnDismissListener {
            popupMenu.dismiss()
        }

        popupMenu.show()
    }

}