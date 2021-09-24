package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoFinalizado;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoPendiente;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoSinFinalizar;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;

public class ContractHistoryActivity extends AppCompatActivity {
    private ListView listView_ContratosPendientes;
    private ListView listView_ContratosSinFinalizar;
    private ListView listView_ContratosFinalizados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_history);
        setTitle("Historial de contratos");

        listView_ContratosPendientes = findViewById(R.id.listView_ContratosPendientes);
        listView_ContratosSinFinalizar = findViewById(R.id.listView_ContratosSinFinalizar);
        listView_ContratosFinalizados = findViewById(R.id.listView_ContratosFinalizados);

        AdapterListViewContratoPendiente adapterListViewCP = new AdapterListViewContratoPendiente(ContractHistoryActivity.this, R.layout.item_contrato_pendiente, DatosPrueba.getListContratosPendientes());
        listView_ContratosPendientes.setAdapter(adapterListViewCP);

        AdapterListViewContratoSinFinalizar adapterListViewCSF = new AdapterListViewContratoSinFinalizar(ContractHistoryActivity.this, R.layout.item_contrato_sin_finalizar, DatosPrueba.getListContratosSinFinalizar());
        listView_ContratosSinFinalizar.setAdapter(adapterListViewCSF);

        AdapterListViewContratoFinalizado adapterListViewCF = new AdapterListViewContratoFinalizado(ContractHistoryActivity.this, R.layout.item_contratos_finalizados, DatosPrueba.getListContratosFinalizados());
        listView_ContratosFinalizados.setAdapter(adapterListViewCF);
    }
}