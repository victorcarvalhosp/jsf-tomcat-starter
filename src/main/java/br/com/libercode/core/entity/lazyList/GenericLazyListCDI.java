package br.com.libercode.core.entity.lazyList;

import br.com.libercode.core.bo.AbstractCrudBO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class GenericLazyListCDI<T> extends LazyDataModel<T> implements SelectableDataModel<T>, Serializable {

	protected List<T> lista;
	protected T pesquisa;
	protected AbstractCrudBO<T> bo;
	protected Class<T> clazz;
	protected int total;
	protected boolean IrPaginaUm;

	private static final long serialVersionUID = 1L;

	public GenericLazyListCDI(Class<T> clazz, AbstractCrudBO<T> bo, T pesquisa) {
		this.pesquisa = pesquisa;
		this.clazz = clazz;
		this.bo = bo;
		this.total = bo.total(pesquisa);
	}
	
	
	@Override
    public List<T> load(int posicaoPrimeiraLinha, int maximoPorPagina, String ordenarPeloCampo,
            SortOrder ordernarAscOuDesc, Map<String, Object> filters) {

		if (getRowCount() <= 0) {
			total = bo.total(pesquisa);
			setRowCount(total);
			//posicaoPrimeiraLinha = 0;
		}
		
		if (ordenarPeloCampo == null) {
            ordenarPeloCampo = "tab.registro";
        }
		String ordenacao = ordenarASCouDESC(ordernarAscOuDesc);
		lista = bo.buscar(pesquisa, posicaoPrimeiraLinha, maximoPorPagina,
				ordenarPeloCampo, ordenacao);

		setPageSize(maximoPorPagina);
		
		return lista;
	}
	
	@Override
	public Object getRowKey(T entidade) {
		return entidade.hashCode();
	}

	@Override
	public T getRowData(String sid) {
		Long id = Long.valueOf(sid);
		for (T entidade : lista) {
			Long idEntidade;
			try {
				idEntidade = (Long) entidade.getClass().getMethod("getId").invoke(entidade);
				if (id.equals(idEntidade)) {
					return entidade;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else
			super.setRowIndex(rowIndex % getPageSize());
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


	public int getTotal() {
		return total;
	}
    
}
