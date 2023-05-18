<template>
	<view class="page">
		<van-nav-bar
			title="投资记录"
			left-arrow
			:border="false"
			fixed
			safe-area-inset-top
			@click-left="$base.BackPage('/pages/personal')"
			>
		</van-nav-bar>
		<view class="wrap">
			<table class="table-data">
				<tr>
					<th>项目名称</th>
					<th class="table-money">投资金额</th>
					<th>状态</th>
					<th>详情</th>
					<th>合同</th>
				</tr>
				<tr>
					<td>每日签到，获得奖励2元</td>
					<td class="table-money">
						<!-- <label class="green-text">+2</label> -->
						<label class="red-text">30000</label>
					</td>
					<td class="table-time">已完成</td>
					<td class="table-btn">
						<label class="blue-text" @click="goInvestmentDetails">查看</label>
					</td>
					<td class="table-btn">
						<label class="grey-text">查看</label>
					</td>
				</tr>
			</table>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			goInvestmentDetails(){
				uni.navigateTo({
					url: "/pages/InvestmentDetails",
				});
    		}
		}
	}
</script>

<style scoped lang="scss">
.wrap{
	padding-top: 104upx;
	.table-data{
		width: 100%;
		border-collapse: collapse;
		font-size: 24upx;
		text-align: center;
		th{
			height: 50upx;
			text-align: center;
		}
		th,td{
			border-right: 1px solid #eee;
			border-bottom: 1px solid #eee;
		}
		.table-money{
			width: 18%;
			.green-text{
				color: green;
			}
			.red-text{
				color: red;
			}
		}
		.table-time{
			width: 13%;
			padding: 16upx 4upx;
		}
		.table-btn{
			label{
				font-size: 12px;
				padding: 5px 5px;
				color: #fff;
				border-radius: 5px;
			}
			.grey-text{
				background-color: #567da8;
			}
			.blue-text{
				background-color: #4994ec;
			}
		}
	}
}
</style>
