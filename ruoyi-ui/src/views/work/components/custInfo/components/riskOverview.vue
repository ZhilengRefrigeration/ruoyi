<template>
	<div class="riskOverview">
		<header-Name :name="headerTitle"></header-Name>
		<div class="content">
			<div class="riskSignal">
				<div class="left">
					<img style="width: 40px;height: 40px;"
						:src="require('@/assets/img/work/custInfo/riskOverviewBgroundSigle.png')" class="img" alt="" />
					<div class="self">
						<span class="text">自身风险信号数：</span>
						<span class="number">82</span>
					</div>
					<div class="time">
						<span class="text">更新时间：</span>
						<span class="number">2022-10-14</span>
					</div>
				</div>
				<div class="right">
					<span>
						自身风险信号为<span class="span">82</span>个,
						特大风险信号为<span class="span">0</span>个,
						预警风险信号为<span class="span">1</span>个,
						关注风险信号为<span class="span">1</span>个,
						提示风险信号为<span class="span">80</span>个;
						涉及关联风险的数量为<span class="span">2226</span>个,
						其中涉及特大风险的为<span class="span">5</span>个,
						涉及预警风险的为<span class="span">13</span>个,
						涉及关注风险的为<span class="span">27</span>个,
						涉及提示风险的为<span class="span">2131</span>个。
					</span>
				</div>
			</div>
			<div class="detail">
				<el-row>
					<el-col :span="8">
						<div class="selfRisk detailItem">
							<div class="title">
								<div class="blueLine"></div>
								<div class="name">关联实体</div>
								<img 
								  :src="require('@/assets/img/work/custInfo/riskOverview_sigh.png')" 
								  alt=""
								/>
								<span>共累计<span class="spanYellow"> 2226 </span>条风险</span>
							</div>
							<div class="riskList">
								<div class="listTitle">
									<img
									  :src="require('@/assets/img/work/custInfo/riskOverview_iconRisk1.png')" 
									  alt=""
									/>
									<div class="text">特大风险</div>
									<div class="dot"></div>
									<div class="number">3</div> 
								</div>
								<div class="listLabel">
									<div class="labelItem">破产清算</div>
									<div class="labelItem">破产清算</div>
									<div class="labelItem">破产清算</div>
									<div class="labelItem">破产清算</div>
									<div class="labelItem">破产破产清算清算</div>
									<div class="labelItem">破产破产清算清算</div>
									<div class="labelItem">破产清算</div> 
								</div>
								<div class="explain">
									<img
									  :src="require('@/assets/img/work/custInfo/riskOverview_sigh.png')" 
									  alt=""
									/>
									以上标签包含<span class="number">5</span>条风险
								</div>
							</div>
						</div>
					</el-col>
					<el-col :span="8">
						<div class="relationEntity detailItem">
							<div class="title">
								<div class="blueLine"></div>
								<div class="name">关联实体</div>
								<img 
								  :src="require('@/assets/img/work/custInfo/riskOverview_sigh.png')" 
								  alt=""
								/>
								<span>共累计<span class="spanBlack"> 1111 </span>条,其中风险实体<span class="spanYellow"> 81 </span>个</span>
							</div>
						</div>
					</el-col>
					<el-col :span="8">
						<div class="relationRisk detailItem">
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		mapGetters
	} from 'vuex'
	import HeaderName from './headerName.vue';
	export default {
		name: 'riskOverview', //风险总览
		components: {
			HeaderName
		},
		data() {
			return {
				headerTitle: "风险总览",
				selfRisk:{
					total:2226,
					risk:[{
						img:require('@/assets/img/work/custInfo/riskOverview_iconRisk1.png'),
						name:"特大风险",
						riskNum:3,
						labelNum:5,
						labelList:[]
						
						
					}]
				}
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
	.riskOverview {
		max-width: 1200px;
		width: 100%;
		height: 760px;
		background-color: #FFFFFF;
		background-image: url('../../../../../assets/img/work/custInfo/riskOverviewBground.png');
		background-size: 100% 100%;

		.content {
			margin-top: 16px;
			width: 100%;
			height: calc(100% - 72px);
			box-sizing: border-box;
			padding-top: 16px;
			padding: 0px 24px;
			.detail{
				margin-top: 62px;
				width: 100%;
				.detailItem{
					min-height: 450px; 
					.riskList{ 
						.listLabel{
							min-height: 16px; 
							width: 90%; 
							display: flex;
							flex-wrap:wrap;
							.labelItem{ 
								min-width: 60px;
								height: 20px;
								background: rgba(254,106,65,0.1);
								border-radius: 2px; 
								font-size: 12px; 
								font-weight: 400;
								color: #FE6A41; 
								line-height: 20px;
								margin-bottom: 10px;
								margin-right: 10px;
								box-sizing: border-box;
								padding: 0px 6px;
								text-align: center;
							}
							
						}
						.explain{
							width: 100%;
							height: 16px;
							line-height: 16px;
							color: #888888;
							font-size: 12px;
							line-height: 16px; 
							margin-top: 2px;
							margin-bottom: 32px;
							img{
								height: 18px;
								width: 18px; 
								margin-top: -4px;
							}
							.number{
								color: #555555;
								font-size: 16px;
								margin: 0px 3px;
							}
						}
						.listTitle{
							height: 16px;
							width: 100%;
							display: flex;
							align-items: center;
							margin-bottom: 16px;
							img{
								height: 14px;
								width: 14px;
								margin-right: 10px;
							}
							.dot{
								width: 4px;
								height: 4px;
								background: #FE6A41;
								margin: 0px 6px;
							}
							.number{ 
								font-size: 16px; 
								font-weight: 500;
								color: #222222;  
								margin-top: 2px;
							}
							.text{ 
								font-size: 14px; 
								font-weight: 400;
								color: #222222; 
							}
						}
					}
					.title{
						height:14px ;
						width: 100%;
						display: flex;
						align-items: center;
						margin-bottom: 24px;
						img{
							width: 20px;
							height: 20px;
							margin-left: 10px;
							margin-right: 4px;
						}
						.blueLine{
							width: 4px;
							height: 14px;
							background: #096DD9;
							border-radius: 2px; 
						}
						.name{ 
							font-size: 14px; 
							font-weight: bold;
							color: #222222; 
							margin-left: 10px;
						}
						span{
							color:#888888 ; 
							.spanBlack{
								color:#555555;
							}
							.spanYellow{
								color:#FA6400;
							}
						}
						
						
					}
				}
			}
			.riskSignal {
				height: 68px;
				width: 100%;
				display: flex;
				flex-direction: row;
				align-items: center;

				.right {
					height: 68px;
					flex: 1;
					box-sizing: border-box;
					padding-left: 24px;
					display: flex;
					align-items: center;
					color: #888888;
					font-size: 14px;
					line-height: 20px;

					.span {
						color: #FA6400;
					}
				}

				.left {
					margin-left: 14px;
					width: 240px;
					height: 68px;
					background: #03C6CC;
					border-radius: 42px;
					padding-left: 50px;
					box-sizing: border-box;
					position: relative;

					.img {
						position: absolute;
						left: -14px;
						top: 14px;
					}

					.self {
						margin-top: 11px;
						display: flex;
						align-items: center;
						height: 18px;
						font-size: 18px;
						font-weight: 500;
						color: #FFFFFF;

						.text {
							font-weight: 400;
							font-size: 14px;
						}

						.number {
							margin-left: 6px;
						}
					}

					.time {
						height: 14px;
						font-size: 14px;
						font-weight: 400;
						color: #FFFFFF;
						margin-top: 10px;

						.number {
							margin-left: 6px;
						}
					}
				}
			}
		}

	}
</style>
