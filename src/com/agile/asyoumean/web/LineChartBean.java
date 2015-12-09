package com.agile.asyoumean.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.model.externalmodel.MatchRatioItem;

@ViewScoped
@ManagedBean(name = "lineChartBean")
public class LineChartBean {
	
    private CartesianChartModel model;
    private List<MatchRatioItem> ratioItemList;

    @PostConstruct
	public void init() {

		ratioItemList = CoreDAO.getInstance().getMatchRatio();

		model = new CartesianChartModel();
		ChartSeries values = new ChartSeries();
		values.setLabel("Algorithm match percent");

		for (MatchRatioItem item : ratioItemList) {

			values.set(item.getSegm(), item.getCnt());

		}

		model.addSeries(values);

	}

    public CartesianChartModel getModel() {

        return model;

    }

	public List<MatchRatioItem> getRatioItemList() {
		return ratioItemList;
	}

	public void setRatioItemList(List<MatchRatioItem> ratioItemList) {
		this.ratioItemList = ratioItemList;
	}

}
