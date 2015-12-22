package com.agile.asyoumean.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.agile.asyoumean.dao.CoreDAO;
import com.agile.asyoumean.model.externalmodel.MatchRatioItem;
import com.agile.asyoumean.model.externalmodel.StatisticsItem;

@ViewScoped
@ManagedBean(name = "lineChartBean")
public class LineChartBean {

	private List<MatchRatioItem> ratioItemList;

	private LineChartModel lineModel;

	private BarChartModel barModel;

	@PostConstruct
	public void init() {
		createLineModels();
		createBareModels();
	}

	public List<MatchRatioItem> getRatioItemList() {
		return ratioItemList;
	}

	public void setRatioItemList(List<MatchRatioItem> ratioItemList) {
		this.ratioItemList = ratioItemList;
	}

	private void createLineModels() {
		lineModel = initLinearModel();
		lineModel.setTitle("Linear Chart");

		lineModel.setShowPointLabels(true);
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(800);

		Axis xAxis = lineModel.getAxis(AxisType.X);

		xAxis.setMin(0);
		xAxis.setMax(120);

	}

	private void createBareModels() {
		barModel = initBarModel();

		barModel.setTitle("Most Searched Wrong Words Detail");
		barModel.setLegendPosition("ne");

		Axis xAxisb = barModel.getAxis(AxisType.X);
		xAxisb.setLabel("Searched Words");
		xAxisb.setMin(0);
		xAxisb.setMax(100);

		Axis yAxisb = barModel.getAxis(AxisType.Y);
		yAxisb.setLabel("Number of Search");
		yAxisb.setMin(0);
		yAxisb.setMax(100);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();

		ratioItemList = CoreDAO.getInstance().getMatchRatio();
		for (MatchRatioItem item : ratioItemList) {

			series1.set(item.getSegm(), item.getCnt());

		}
		series1.setLabel("Algorithm match percent");

		model.addSeries(series1);

		return model;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		
//		List<StatisticsItem> list = CoreDAO.getInstance()
//				.getStatisticsForCommon("ABA");
//		
//		ChartSeries ss;
//int i=0;
//		for (StatisticsItem statisticsItem : list) {
//			ss = new ChartSeries();
//			ss.setLabel(statisticsItem.getGivenWord());
//			ss.set(statisticsItem.getGivenWord(),
//					statisticsItem.getMatchedWordCount());
//			model.addSeries(ss);
//			i++;
//			if(i==1){
//				return model;
//				
//			}
//		}


		return model;
	}

	public void barGoster() {
		
		String secilenMatchedWorld = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("log2Param");

		
		List<StatisticsItem> list = CoreDAO.getInstance()
				.getStatisticsForCommon(secilenMatchedWorld);

		barModel.clear();
		ChartSeries ss;
	

		for (StatisticsItem statisticsItem : list) {
			ss = new ChartSeries();
			ss.setLabel(statisticsItem.getGivenWord());
			ss.set("",
					statisticsItem.getGivenWordCount());
			barModel.addSeries(ss);
			
		}
		

	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

}
