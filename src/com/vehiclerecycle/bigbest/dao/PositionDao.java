package com.vehiclerecycle.bigbest.dao;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.vehiclerecycle.bigbest.dao.inf.BaseDao;
import com.vehiclerecycle.bigbest.dao.inf.InterfaceDao;
import com.vehiclerecycle.bigbest.entities.Position;

@Repository
public class PositionDao extends BaseDao implements InterfaceDao<Position> {
	private static  Logger getlogger=Logger.getLogger("com.vehiclerecycle.bigbest.dao.PositionDao");

	@Override
	public void saveOrUpdate(HttpSession session,Position t) {
		if(t.getId()==null){
			   getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",添加职位:"+t.getPositionName()+",职位管理");
		}else{
			getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",修改职位:"+t.getPositionName()+",职位管理");
		}
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delete(HttpSession session,Position t) {
		String hql="DELETE Position p WHERE p.id=:id";
		this.getSession().createQuery(hql).setInteger("id", t.getId()).executeUpdate();
		getlogger.error(session.getAttribute(EMPLOYEE_NAME)+",删除职位:"+t.getPositionName()+",职位管理");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getAll() {
		String hql="FROM Position";
		List<Position> positions=this.getSession().createQuery(hql).list();
		return positions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getByInfo(Position t) {
		List<Position> positions;
		if (t.getId()!=null) {
			String hql="FROM Position p WHERE p.id=:id";
			positions=this.getSession().createQuery(hql).setInteger("id",t.getId()).list();
		}
		else if (t.getPositionName()!=null) {
			String hql="FROM Position p WHERE p.positionName like :positionName";
			positions=this.getSession().createQuery(hql).setString("positionName","%"+ t.getPositionName()+"%").list();
		}
		else if (t.getDepartment()!=null) {
			String hql="FROM Position p WHERE p.department.deptName like :department";
			positions=this.getSession().createQuery(hql).setString("department","%"+ t.getDepartment().getDeptName()+"%").list();
		}
		else {
			positions=this.getAll();
		}
		return positions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position getOneById(Integer id) {
		List<Position> positions;
		String hql="FROM Position p WHERE p.id=:id";
		positions=this.getSession().createQuery(hql).setInteger("id",id).list();
		return positions.isEmpty()?(new Position()):positions.get(0);
	}

}
