package br.com.rodrigo.agendinha.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.rodrigo.agendinha.ListaAlunosActivity;
import br.com.rodrigo.agendinha.R;
import br.com.rodrigo.agendinha.modelo.Aluno;

/**
 * Created by Rodrigo on 8/12/2016.
 */
public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Aluno aluno = alunos.get(i);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = view;
        if (view == null) {

            view1 = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        TextView campoNome = (TextView) view1.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = (TextView) view1.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());


        TextView campoEndereco = (TextView) view1.findViewById(R.id.item_endereco);
        if (campoEndereco != null) {
            campoEndereco.setText(aluno.getEndereco());
        }

        TextView campoSite = (TextView) view1.findViewById(R.id.item_site);
        if (campoSite != null) {
            campoSite.setText(aluno.getSite());
        }


        ImageView campoFoto = (ImageView) view1.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return view1;
    }
}
