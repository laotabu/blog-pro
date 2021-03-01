<template>
  <div class="login clearfix">
    <div class="login-wrap">
      <el-row type="flex" justify="center">
        <el-form ref="loginForm" :model="user" status-icon label-width="80px">
          <h3>添加用户</h3>
          <hr>
          <el-form-item label="用户名称" >
            <el-input v-model="user.name" placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item label="用户密码">
            <el-input v-model="user.password" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item label="用户邮箱" >
            <el-input v-model="user.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item label="用户姓名" >
            <el-input v-model="user.nickname" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="用户角色" >
            <el-checkbox-group v-model="user.role">
              <el-checkbox label="超级管理员" name="role"></el-checkbox>
              <el-checkbox label="普通用户" name="role"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="用户状态">
              <el-switch
              v-model="user.enabled"
              active-text="启用"
              active-color="#13ce66"
              inactive-text="禁用" style="font-size: 12px">
            </el-switch>
          </el-form-item>
          <el-form-item label="上传头像">
            <el-upload class="avatar-uploader" action="api/"
              :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="user.imageUrl" :src="user.imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">添加用户</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
  </div>
</template>
 
<script>
  import {isNotNullORBlank} from '../utils/utils'
  import {postRequest} from '../utils/api'
    export default {
        data() {
            return {
                user: {
                    name: '',
                    password: '',
                    email: '',
                    nickname: '',
                    role: '',
                    enable: '',
                    imageUrl: '',
                }
            }
        },
        methods: {
           handleAvatarSuccess(res, file) {
           this.user.imageUrl = URL.createObjectURL(file.raw);
           },
           beforeAvatarUpload(file) {
           const isJPG = file.type === 'image/jpeg';
           const isLt2M = file.size / 1024 / 1024 < 2; if (!isJPG) { this.$message.error('上传头像图片只能是 JPG 格式!'); } if
             (!isLt2M) { this.$message.error('上传头像图片大小不能超过 2MB!'); } return isJPG && isLt2M; },
          onSubmit() {
          // console.log(this.user.type);
          if (!(isNotNullORBlank(this.user.name, this.user.password, this.user.email, this.user.nickname,
          this.user.enable,this.user.role))) {
          this.$message({type: 'error', message: '数据不能为空!'});
          return;
          }
          var _this = this;
          _this.loading = true;
          postRequest("admin/user/register", {
          user: _this.user.name,
          password: _this.user.password,
          email: _this.user.email,
          nickname: _this.user.nickname,
          role: _this.user.role,
          enable: _this.user.enable
          }).then(resp=> {
          _this.loading = false;
          if (resp.status == 200 && resp.data.status == 'success') {
          console.log(data);
          _this.$message({type: 'success', message: state == 0 ? '添加成功!' : '发布成功!'});
          // if (_this.from != undefined) {
          }})
          }
        }
    }
</script>

<style>
    .home_container {
        height: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
    }

    .el-header {
        background-color: #DEB887;
        color: #333;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .el-aside {
        background-color: #ECECEC;
    }

    .el-main {
        background-color: #fff;
        color: #000;
        text-align: center;
    }

    .home_title {
        color: #fff;
        font-size: 22px;
        display: inline;
    }

    .home_userinfo {
        color: #fff;
        cursor: pointer;
    }

    .home_userinfoContainer {
        display: inline;
        margin-right: 20px;
    }

    .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 华文行楷;
        color: #DEB887;
        padding-top: 50px;
    }
</style>