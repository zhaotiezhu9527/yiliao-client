<template>
	<view class="page">
		<van-nav-bar
			title="实名认证"
			left-arrow
			:border="false"
			fixed
			safe-area-inset-top
			@click-left="$base.BackPage('/pages/personal')"
			>
		</van-nav-bar>
		<view class="wrap">
			<view>
				<label>姓名</label>
				<input type="text" style="display: inline-block;" placeholder="请输入真实姓名"/>
			</view>
			<view>
				<label>身份证号码</label>
				<input type="text" placeholder="请输入身份证号码"/>
			</view>
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
			
		}
	}
</script>

<style scoped lang="scss">
.wrap{
	padding-top: 104upx;
	
}
</style>
