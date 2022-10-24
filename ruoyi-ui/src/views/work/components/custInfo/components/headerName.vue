<!-- 
  用法： 
  <header-Name :name="headerTitle"></header-Name>  
  
    import HeaderName from './headerName.vue';
	components: {
		HeaderName
	}, 
 
 -->

<template>
  <div class="header">
  	<el-row>
  		<el-col :span="12">
  			<div class="col_content">
  				<img 
  				  style="width: 16px;height: 16px;"
  				  :src="require('@/assets/img/work/custInfo/module_header_left.png')"
  				  class="img"
  				  alt=""
  				/>
  				<span style="margin-left: 10px;">{{name}}</span>
  			</div>
  		</el-col>
  		<el-col :span="12">
  			<div  class="col_content" style="justify-content: flex-end;">
  				<img
  				   style="width: 24px;height: 24px;"
  				  :src="require('@/assets/img/work/custInfo/module_header_share.png')"
  				  class="img"
  				  alt=""
  				/>
  			</div>
  		</el-col>
  	</el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex' 
export default {
  name: 'work',
  props: { 
    name:{
      type:String,
      default:''
    },
  },
  components: {

  },
  data() {
    return {

    }
  },
  computed: {
    ...mapGetters({
      codeCombo: 'getCodeCombo',
      permissionList: 'getPermission',
      userInfo: 'getUserInfo',
    }),
  },
  activated() {

  },
  created() {

  },
  methods: {

  },
}
</script>

<style lang="scss" scoped>
	.header {
		width: 100%;
		height: 72px; 
		box-sizing: border-box;
		padding: 0px 24px;
		.col_content{ 
			height: 72px;
			width: 100%;
			display: flex;
			justify-content: flex-start;
			align-items: center; 
			font-size: 15px; 
			font-weight: bold;
			color: #096DD9; 
		}
	
	}
	
</style>
