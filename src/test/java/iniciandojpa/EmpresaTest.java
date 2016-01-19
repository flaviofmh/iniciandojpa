package iniciandojpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.daos.EmpresaDAO;
import br.com.pojos.Empresa;

public class EmpresaTest {
	
	@Test
	public void buscarEmpresasJPQL() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		List<Empresa> empresaList = empresaDAO.buscarEmpresasJPQL();
		Assert.assertNotNull(empresaList);
	}
	
	@Test
	public void buscarEmpresasCriteria() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		List<Empresa> empresaList = empresaDAO.buscarEmpresasCriteria();
		Assert.assertNotNull(empresaList);
	}
	
	@Test
	public void consultarEmpresasUsandoJPQL() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", "Silva");
		List<Empresa> empresaList = empresaDAO.buscarEmpresaPorClienteJPQL(parametros);
		Assert.assertNotNull(empresaList);
	}
	
	@Test
	public void consultarEmpresasUsandoCriteria() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", "Silva");
		List<Empresa> empresaList = empresaDAO.buscarEmpresaPorClienteCriteria(parametros);
		Assert.assertNotNull(empresaList);
	}

}
