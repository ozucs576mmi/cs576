package com.agile.asyoumean.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import com.agile.asyoumean.util.WorldUtil;

@ViewScoped
@ManagedBean(name = "chartBean")
public class ChartBean {

	private PieChartModel pieModel;

	@PostConstruct
	public void init() {
		pieModel = new PieChartModel();
		pieModel.set("kaðýt", 12);
		pieModel.set("kalem", 25);
		pieModel.set("silgi", 37);
		pieModel.set("asa", 100);
		pieModel.set("masa", 2);
		pieModel.set("ada", 5);
		pieModel.set("laptop", 80);
		pieModel.set("kart", 20);
		pieModel.set("diðerleri", 150);
	}

	public PieChartModel getPieModel() {

		return pieModel;

	}

	public void cacheRefreshAction() {

		// WorldUtil.getInstance().getWorldList();
		//
		// addMessage("Cache refresh success...");
		//
	}

	public void addMessage(String summary) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

}
