<template>
    <div class="navbar">
        <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container"
                   @toggleClick="toggleSideBar"/>

        <breadcrumb id="breadcrumb-container" class="breadcrumb-container"/>

        <div class="right-menu">
            <template v-if="device!=='mobile'">
                <search id="header-search" class="right-menu-item"/>

                <el-tooltip content="源码地址" effect="dark" placement="bottom">
                    <ruo-yi-git id="ruoyi-git" class="right-menu-item hover-effect"/>
                </el-tooltip>

                <el-tooltip content="文档地址" effect="dark" placement="bottom">
                    <ruo-yi-doc id="ruoyi-doc" class="right-menu-item hover-effect"/>
                </el-tooltip>

                <screenfull id="screenfull" class="right-menu-item hover-effect"/>

                <el-tooltip content="布局大小" effect="dark" placement="bottom">
                    <size-select id="size-select" class="right-menu-item hover-effect"/>
                </el-tooltip>

            </template>

            <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
                <div class="avatar-wrapper">
                    <img :src="avatar" class="user-avatar">
                    <i class="el-icon-caret-bottom"/>
                </div>
                <el-dropdown-menu slot="dropdown">
                    <router-link to="/user/profile">
                        <el-dropdown-item>个人中心</el-dropdown-item>
                    </router-link>
                    <el-dropdown-item @click.native="cutSysHandle">
                        <span>切换系统</span>
                    </el-dropdown-item>
                    <el-dropdown-item @click.native="setting = true">
                        <span>布局设置</span>
                    </el-dropdown-item>
                    <el-dropdown-item divided @click.native="logout">
                        <span>退出登录</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>


        <el-dialog
            title="系统切换"
            :visible.sync="centerDialogVisible"
            width="50%"
            center>
            <el-row :gutter="20">
                <el-col :span="8"
                    v-for="(sys, index) in menuSysList"
                    :key="sys.path  + index"
                    :item="sys"
                >
                    <div class="grid-content bg-purple" :class="[sys.menuId == activeMenuSysId?'active':'']" @click="clickChange(sys)">
                        <div  style="text-align: center;padding:5px 0">
                            <img style="width: 100px;height: 100px;border-radius: 50%" src="@/assets/image/1.jpg">
                            <p style="margin-bottom: 0">{{sys.menuName}}</p>
                        </div>
                    </div>
                </el-col>
            </el-row>
            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitCutSys">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import Breadcrumb from '@/components/Breadcrumb'
    import Hamburger from '@/components/Hamburger'
    import Screenfull from '@/components/Screenfull'
    import SizeSelect from '@/components/SizeSelect'
    import Search from '@/components/HeaderSearch'
    import RuoYiGit from '@/components/RuoYi/Git'
    import RuoYiDoc from '@/components/RuoYi/Doc'
    import {listMenuSys} from "@/api/system/menu";
    import {editSystem,getUserProfile} from "@/api/system/user";

    export default {
        components: {
            Breadcrumb,
            Hamburger,
            Screenfull,
            SizeSelect,
            Search,
            RuoYiGit,
            RuoYiDoc
        },
        data() {
            return {
                centerDialogVisible: false,
                menuSysList: [],
                activeMenuSysId: 0,
                user: {},
            }
        },
        computed: {
            ...mapGetters([
                'sidebar',
                'avatar',
                'device'
            ]),
            setting: {
                get() {
                    return this.$store.state.settings.showSettings
                },
                set(val) {
                    this.$store.dispatch('settings/changeSetting', {
                        key: 'showSettings',
                        value: val
                    })
                }
            }
        },
        created() {
            // 获取当前登录用户系统
            getUserProfile().then(response => {
                this.user = response.data;
                if(this.user.systemId != null){
                    this.activeMenuSysId = this.user.systemId;
                }
            });
            // 获取用户所属系统
            listMenuSys().then(response => {
                this.menuSysList = response.data;
            });
        },
        methods: {
            submitCutSys(){
                this.user.systemId = this.activeMenuSysId;
                this.centerDialogVisible = false;
                editSystem({
                    ...this.user
                }).then(() => {
                    this.$message.info("修改成功");
                    window.location.href = "/";
                })
            },
            cutSysHandle() {
                this.centerDialogVisible = true;
            },
            clickChange(item){
                this.seleMenuSys = item;
                this.activeMenuSysId = item.menuId;
            },
            toggleSideBar() {
                this.$store.dispatch('app/toggleSideBar')
            },
            async logout() {
                this.$confirm('确定注销并退出系统吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('LogOut').then(() => {
                        location.href = '/index';
                    })
                })
            }
        }
    }
</script>

<style lang="scss" scoped>
    .navbar {
        height: 50px;
        overflow: hidden;
        position: relative;
        background: #fff;
        box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

        .active{
            background-color: #409EFF;
        }

        .hamburger-container {
            line-height: 46px;
            height: 100%;
            float: left;
            cursor: pointer;
            transition: background .3s;
            -webkit-tap-highlight-color: transparent;

            &:hover {
                background: rgba(0, 0, 0, .025)
            }
        }

        .breadcrumb-container {
            float: left;
        }

        .errLog-container {
            display: inline-block;
            vertical-align: top;
        }

        .right-menu {
            float: right;
            height: 100%;
            line-height: 50px;

            &:focus {
                outline: none;
            }

            .right-menu-item {
                display: inline-block;
                padding: 0 8px;
                height: 100%;
                font-size: 18px;
                color: #5a5e66;
                vertical-align: text-bottom;

                &.hover-effect {
                    cursor: pointer;
                    transition: background .3s;

                    &:hover {
                        background: rgba(0, 0, 0, .025)
                    }
                }
            }

            .avatar-container {
                margin-right: 30px;

                .avatar-wrapper {
                    margin-top: 5px;
                    position: relative;

                    .user-avatar {
                        cursor: pointer;
                        width: 40px;
                        height: 40px;
                        border-radius: 10px;
                    }

                    .el-icon-caret-bottom {
                        cursor: pointer;
                        position: absolute;
                        right: -20px;
                        top: 25px;
                        font-size: 12px;
                    }
                }
            }
        }
    }

    .el-row {
        margin-bottom: 20px;
        &:last-child {
            margin-bottom: 0;
        }
    }
    .el-col {
        border-radius: 4px;
    }
    .bg-purple-dark {
        background: #99a9bf;
    }
    .bg-purple {
        background: #d3dce6;
    }
    .bg-purple-light {
        background: #e5e9f2;
    }
    .grid-content {
        /*text-align: center;*/
        border-radius: 4px;
        min-height: 36px;
    }
    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
</style>
