package br.com.iniciarmenu;

import java.util.ArrayList;

import br.com.daos.EmpresaDAO;
import br.com.pojos.Cliente;
import br.com.pojos.Empresa;

public class Menu {

	public static void main(String[] args) {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		Empresa empresa = new Empresa("Dogs & CIA");
		empresa.setClientes(new ArrayList<Cliente>());
		{
			Cliente cliente = new Cliente("Tihuana Silva", empresa);
			empresa.getClientes().add(cliente);
		}
		{
			Cliente cliente = new Cliente("Pedrito Santos", empresa);
			empresa.getClientes().add(cliente);
		}
		{
			Cliente cliente = new Cliente("Marta Catraca", empresa);
			empresa.getClientes().add(cliente);
		}
		
		empresaDAO.salvar(empresa);
		System.out.println(empresa.toString());
	}
	
}
