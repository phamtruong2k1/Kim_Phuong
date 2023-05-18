package com.kimphuong.manage.ui.statistic

import android.graphics.Canvas
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.renderer.PieChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class CustomPieChart : PieChartRenderer {
    constructor(
        chart: PieChart,
        chartAnimator: ChartAnimator,
        viewPortHandler: ViewPortHandler
    ) : super(chart, chartAnimator, viewPortHandler)

    override fun drawEntryLabel(c: Canvas?, label: String?, x: Float, y: Float) {
        val adjustX = x + 10
        val adjustY = y + 30
        super.drawEntryLabel(c, label, adjustX, adjustY)
    }

    override fun drawValue(c: Canvas?, valueText: String?, x: Float, y: Float, color: Int) {
        val adjustX = x + 20
        val adjustY = y + 125
        super.drawValue(c, valueText, adjustX, adjustY, color)
    }

}