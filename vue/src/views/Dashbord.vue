<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" /> 学生总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ studentNum }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-user-solid" /> 教师数量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{teacherNum}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-user-solid" /> 管理数量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ adminNum }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-s-shop" /> 文章数量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ articleNum }}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px;padding-left: 50px"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      studentNum:0,
      teacherNum:0,
      articleNum:0,
      adminNum:0,
      list:[]
    }
  },
  mounted() {  // 页面元素渲染之后再触发
    var option = {
        title: {
          text: '用户数量统计',
          subtext: '柱状图',
          left: 'center'
        },
      xAxis: {
        type: 'category',
        data: ['学生','教师','管理']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'bar'
        },
        {
          data: [],
          type: 'line'
        }
      ]
    };

    // 饼图

    var pieOption = {
      title: {
        text: '用户数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          // name: "瑞幸咖啡",
          type: 'pie',
          radius: '60%',
          // center: ['75%', '50%'],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ],

    };


    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);
    //
    this.request.get("/user").then(res => {
      this.studentNum = res.data.filter(v => v.role ==='ROLE_STUDENT').length
      this.teacherNum = res.data.filter(v => v.role ==='ROLE_TEACHER').length
      this.adminNum = res.data.filter(v => v.role ==='ROLE_ADMIN').length

      option.series[0].data=[
        {value: this.studentNum,name: '学生'},
        {value: this.teacherNum,name: '教师'},
        {value: this.adminNum,name: '管理'},
      ]
      option.series[1].data=[
        {value: this.studentNum,name: '学生'},
        {value: this.teacherNum,name: '教师'},
        {value: this.adminNum,name: '管理'},
      ]
      pieOption.series[0].data=[
        {value: this.studentNum,name: '学生'},
        {value: this.teacherNum,name: '教师'},
        {value: this.adminNum,name: '管理'},
      ]

      myChart.setOption(option);
      pieChart.setOption(pieOption)

  })
    this.request.get("/article").then(res=>{
      this.articleNum=res.data.length
    })
  }
}
</script>

<style scoped>

</style>
