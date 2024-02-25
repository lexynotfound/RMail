package com.raihanardila.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raihanardila.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    // Mendeklarasikan RecyclerView dan ListMailAdapter
    private lateinit var listMailAdapter: ListMailAdapter
    private val list = ArrayList<Mail>()


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        // Inisialisasi RecyclerView
//        rvMail = view.findViewById(R.id.rv_mail)
//        rvMail.layoutManager = LinearLayoutManager(activity)
//
//        // Mengatur adapter RecyclerView
//        listMailAdapter = ListMailAdapter(list)
//        rvMail.adapter = listMailAdapter
//
//        return view
//    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMail.setHasFixedSize(true)

        list.addAll(getListMail())
        showRecyclerList()

    }

    // Ambil List Mail
    private fun getListMail(): ArrayList<Mail> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataSubject = resources.getStringArray(R.array.data_subject)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMail = ArrayList<Mail>()
        for (i in dataName.indices) {
            val mail = com.raihanardila.myapplication.Mail(
                dataName[i],
                dataDescription[i],
                dataSubject[i],
                dataPhoto.getResourceId(i, -1)
            )
            listMail.add(mail)
        }
        return listMail
    }

    private fun showRecyclerList() = with(binding) {
        rvMail.layoutManager = LinearLayoutManager(requireContext())
        val listMailAdapter = ListMailAdapter(list)
        rvMail.adapter = listMailAdapter
        listMailAdapter.setOnItemClickCallback(object : ListMailAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Mail) {
                showSelectedMail(data)
            }
        })

    }

    private fun showSelectedMail(mail: Mail) {
        val intentDetail = Intent(requireContext(), DetailActivity::class.java)
        intentDetail.putExtra("key_mail", mail)
        startActivity(intentDetail)
    }
}
