package br.com.libercode.core.entity.lazyList;

import br.com.libercode.core.bo.AbstractCrudBO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericLazyListDataScrollerCDI<T> extends LazyDataModel<T> implements SelectableDataModel<T>, Serializable {

	protected List<T> lista;
	protected T pesquisa;
	protected AbstractCrudBO<T> bo;
	protected Class<T> clazz;
    private int rowIndex;


	private static final long serialVersionUID = 1L;

	public GenericLazyListDataScrollerCDI(Class<T> clazz, AbstractCrudBO<T> bo, T pesquisa) {
		this.pesquisa = pesquisa;
		this.clazz = clazz;
		this.bo = bo;
		this.lista = new ArrayList<>();
	}
	
	
	@Override
    public List<T> load(int posicaoPrimeiraLinha, int maximoPorPagina, String ordenarPeloCampo,
            SortOrder ordernarAscOuDesc, Map<String, Object> filters) {

		if (getRowCount() <= 0) {
			setRowCount(bo.total(pesquisa));
			posicaoPrimeiraLinha = 0;
		}
		
		if (ordenarPeloCampo == null) {
            ordenarPeloCampo = "tab.registro";
        }
		String ordenacao = ordenarASCouDESC(ordernarAscOuDesc);
		
		List<T> retData;
        if (posicaoPrimeiraLinha >= lista.size()) {
            retData = bo.buscar(pesquisa, posicaoPrimeiraLinha, maximoPorPagina,ordenarPeloCampo, ordenacao);
            lista.addAll(retData);
            return retData;
        } else {
            return lista.subList(posicaoPrimeiraLinha, Math.min(posicaoPrimeiraLinha + maximoPorPagina, lista.size()));
        }
	}
	
	@Override
    public void setRowIndex(int index) {
        if (index >= lista.size()) {
            index = -1;
        }
        this.rowIndex = index;
    }

    @Override
    public T getRowData() {
        return lista.get(rowIndex);
    }

    @Override
    public boolean isRowAvailable() {
        if (lista == null) {
            return false;
        }
        return rowIndex >= 0 && rowIndex < lista.size();
    }

	public String ordenarASCouDESC(SortOrder ordernarAscOuDesc) {
		if (SortOrder.UNSORTED.equals(ordernarAscOuDesc)
				|| SortOrder.DESCENDING.equals(ordernarAscOuDesc)) {
			return "DESC";
		} else {
			return "ASC";
		}
	}

    public List<T> getLista() {
        return lista;
    }
	
	
}
