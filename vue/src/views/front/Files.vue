<template>
  <div>

    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px">

          <div style="border: 1px solid #ccc; padding-bottom: 10px">
            <img :src="item.url" alt="" style="width: 100%">
            <div style="color: #dddddd; padding: 10px">{{ item.name }}</div>
            <div style="padding: 10px"><el-button type="primary" @click="down(item.url)">下载</el-button></div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontHome",
  data() {
    return {
      files: []
    }
  },
  created() {
    this.request.get("/file/front/all").then(res => {
      console.log(res.data)
      this.files = res.data.filter(v => v.type === 'docx' || v.type === 'doc' || v.type === 'xlsx')
    })
  },
  methods: {
    down(url){
      window.open(url)
    }
  }
}
</script>

<style>

</style>
