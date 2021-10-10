package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoFinalizado;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoPendiente;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoSinFinalizar;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratosRechazados;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;

public class ContractHistoryActivity extends AppCompatActivity {
    private ListView listView_ContratosRechazados;
    private ListView listView_ContratosSinFinalizar;
    private ListView listView_ContratosFinalizados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_history);
        setTitle("Historial de contratos");

        listView_ContratosRechazados = findViewById(R.id.listView_ContratosrRechazados);
        listView_ContratosSinFinalizar = findViewById(R.id.listView_ContratosSinFinalizar);
        listView_ContratosFinalizados = findViewById(R.id.listView_ContratosFinalizados);

        AdapterListViewContratosRechazados adapterListViewCR = new AdapterListViewContratosRechazados(ContractHistoryActivity.this, R.layout.item_contrato_rechazados, DatosPrueba.getListContratosPendientes());
        listView_ContratosRechazados.setAdapter(adapterListViewCR);

        AdapterListViewContratoSinFinalizar adapterListViewCSF = new AdapterListViewContratoSinFinalizar(ContractHistoryActivity.this, R.layout.item_contrato_sin_finalizar, DatosPrueba.getListContratosSinFinalizar());
        listView_ContratosSinFinalizar.setAdapter(adapterListViewCSF);

        AdapterListViewContratoFinalizado adapterListViewCF = new AdapterListViewContratoFinalizado(ContractHistoryActivity.this, R.layout.item_contratos_finalizados, DatosPrueba.getListContratosFinalizados());
        listView_ContratosFinalizados.setAdapter(adapterListViewCF);
    }
}