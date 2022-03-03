<template>
  <div class="app-container">
    <div class="chinese">
      <h2>{{article.titleChinese}}</h2>
      <span>{{article.contentChinese}}</span>
    </div>
    <el-divider content-position="center">华丽的分割线</el-divider>
    <div class="english">
      <h2>{{article.titleEnglish}}</h2>
      <span>{{article.contentEnglish}}</span>
    </div>

    <div>
      <el-image
        style="width: 200px;height: auto"
        :src="article.pictureUrl"
        ></el-image>
    </div>

  </div>
</template>

<script>
import {getArticle} from "@/api/business/english/article";

export default {
  name: "ArticleDetails",

  data() {
    return {

      id: null,

      article: {},



    }
  },

  created() {
    //初始化id值
    this.id = this.$route.query.id;
    if (this.id) {
      this.getArticle();
    }

  },

  methods: {

    getArticle() {
      this.$modal.loading("请稍候...");

      getArticle(this.id).then(res => {
        this.article = res.data;
        this.$modal.closeLoading()
      }).catch(err =>{
        this.$modal.closeLoading()
      })
    },

  }

}
</script>

<style scoped>
.app-container > div {
  text-align:center;
  margin-top: 50px;
  margin-bottom: 50px;
}
</style>
