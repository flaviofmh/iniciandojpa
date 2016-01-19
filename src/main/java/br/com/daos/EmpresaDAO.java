package br.com.daos;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import util.JPAUtil;
import br.com.pojos.Cliente;
import br.com.pojos.Empresa;

public class EmpresaDAO {
	
	public void salvar(Empresa empresa) {
		EntityManager manager = JPAUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction(); 
		tx.begin();
		manager.persist(empresa);
		tx.commit();
		manager.close();
	}
	
	/**
	 * Buscando todas as empresas usando JPQL
	 * @return List<Empresa>
	 */
	public List<Empresa> buscarEmpresasJPQL() {
		EntityManager manager = JPAUtil.getEntityManager();
		String consulta = "SELECT e FROM Empresa e";
		TypedQuery<Empresa> q = manager.createQuery(consulta, Empresa.class);
		List<Empresa> empresasList = q.getResultList();
		return empresasList;
	}
	
	/**
	 * Buscando todas as empresas usando CriteriaBuilder
	 * @return List<Empresa>
	 */
	public List<Empresa> buscarEmpresasCriteria() {
		EntityManager manager = JPAUtil.getEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);
		Root<Empresa> root = criteria.from(Empresa.class);
		criteria.select(root);
		TypedQuery<Empresa> query = manager.createQuery(criteria);
		List<Empresa> empresaList = query.getResultList();
		return empresaList;
	}
	
	/**
	 * Metodo em JPQL de busca por empresas que contenham clientes com o nome SILVA
	 * @param parametros
	 * @return List<Empresa>
	 */
	public List<Empresa> buscarEmpresaPorClienteJPQL(Map<String, Object> parametros) {
		EntityManager manager = JPAUtil.getEntityManager();
		//JOIN podem ser usados com os relacionamentos OneToOne, ManyToOne, OneToMany, ManyToMany e mapeamentos de colecoes. Por padrão os JOIN sao INNER JOIN
		String consulta = "SELECT e FROM Empresa e JOIN e.clientes c WHERE c.nome LIKE :nome";
		TypedQuery<Empresa> q = manager.createQuery(consulta, Empresa.class);
		String nome = (String) parametros.get("nome");
		q.setParameter("nome", "%"+nome+"%");
		List<Empresa> empresaList = q.getResultList();
		return empresaList;
	}
	
	/**
	 * Metodo Criteria de busca por empresas que contenham clientes com o nome SILVA
	 * @param parametros
	 * @return List<Empresa>
	 */
	public List<Empresa> buscarEmpresaPorClienteCriteria(Map<String, Object> parametros) {
		EntityManager manager = JPAUtil.getEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);
		Root<Empresa> root = criteria.from(Empresa.class);
		Join<Empresa, Cliente> clientes = root.join("clientes");
		Predicate predicat = builder.and(builder.like(clientes.get("nome"), "%" + parametros.get("nome") + "%"));
		criteria.select(root);
		criteria.where(predicat);
		TypedQuery<Empresa> query = manager.createQuery(criteria);
		List<Empresa> empresaList = query.getResultList();
		return empresaList;
	}

}
