<template>
    <div class="container">
        <div class="row">
            <div class="col-sm-10">
                <h1>Books</h1>
                <hr><br><br>
                <alert :message=message v-if="showMessage"></alert>
                <button type="button" class="btn btn-success btn-sm" v-b-modal.book-modal>Add Book</button>
                <br><br>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="customer in customers" :key="customer.id">
                        <td>{{ customer.id }}</td>
                        <td>{{ customer.name }}</td>
                        <td>
                            <div class="btn-group" role="group">
                                <button
                                        type="button"
                                        class="btn btn-warning btn-sm"
                                        v-b-modal.book-update-modal
                                        @click="editCustomer(customer)">
                                    Update
                                </button>
                                <button
                                        type="button"
                                        class="btn btn-danger btn-sm"
                                        @click="onDeleteCustomer(customer)">
                                    Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <b-modal ref="addBookModal"
                 id="book-modal"
                 title="Add a new book"
                 hide-footer>
            <b-form @submit="onSubmit" @reset="onReset" class="w-100">
                <b-form-group id="form-author-group"
                              label="Name:"
                              label-for="form-author-input">
                    <b-form-input id="form-author-input"
                                  type="text"
                                  v-model="addCustomerForm.name"
                                  required
                                  placeholder="Enter author">
                    </b-form-input>
                </b-form-group>
                <b-button-group>
                    <b-button type="submit" variant="primary">Submit</b-button>
                    <b-button type="reset" variant="danger">Reset</b-button>
                </b-button-group>
            </b-form>
        </b-modal>
        <b-modal ref="editBookModal"
                 id="book-update-modal"
                 title="Update"
                 hide-footer>
            <b-form @submit="onSubmitUpdate" @reset="onResetUpdate" class="w-100">
                <b-form-group id="form-id-edit-group"
                              label="Id:"
                              label-for="form-id-edit-input">
                    <b-form-input id="form-id-edit-input"
                                  type="number"
                                  v-model="editForm.id"
                                  required
                                  placeholder="Enter author">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="form-author-edit-group"
                              label="Name:"
                              label-for="form-author-edit-input">
                    <b-form-input id="form-author-edit-input"
                                  type="text"
                                  v-model="editForm.name"
                                  required
                                  placeholder="Enter author">
                    </b-form-input>
                </b-form-group>
                <b-button-group>
                    <b-button type="submit" variant="primary">Update</b-button>
                    <b-button type="reset" variant="danger">Cancel</b-button>
                </b-button-group>
            </b-form>
        </b-modal>
    </div>
</template>

<script>
    import {AXIOS} from './http-common'
    import Alert from './Alert.vue';
    export default {
        data() {
            return {
                customers: [],
                addCustomerForm: {
                    name: '',
                },
                message: '',
                showMessage: false,
                editForm: {
                    id: '',
                    name: ''
                },
            };
        },
        components: {
            alert: Alert,
        },
        methods: {
            getCustomers() {
                // AXIOS.get(this.$router.params.entitiesName)
                AXIOS.get('customers')
                    .then((res) => {
                        this.customers = res.data;
                    })
                    .catch((error) => {
                        // eslint-disable-next-line
                        console.error(error);
                    });
            },
            addCustomer(payload) {
                // AXIOS.post(this.$router.params.entitiesName, payload)
                AXIOS.post('customers', payload)
                        .then(() => {
                        this.getCustomers();
                        this.message = 'Book added!';
                        this.showMessage = true;
                    })
                    .catch((error) => {
                        // eslint-disable-next-line
                        console.log(error);
                        this.getCustomers();
                    });
            },
            initForm() {
                this.addCustomerForm.name = '';
                this.editForm.id = '';
                this.editForm.name = '';
            },
            onSubmit(evt) {
                evt.preventDefault();
                this.$refs.addBookModal.hide();
                const payload = {
                    title: this.addCustomerForm.title,
                };
                this.addCustomer(payload);
                this.initForm();
            },
            onReset(evt) {
                evt.preventDefault();
                this.$refs.addBookModal.hide();
                this.initForm();
            },
            editCustomer(book) {
                this.editForm = book;
            },
            onSubmitUpdate(evt) {
                evt.preventDefault();
                this.$refs.editBookModal.hide();
                const payload = {
                    id: this.editForm.id,
                    name: this.editForm.name
                };
                this.updateCustomer(payload);
            },
            updateCustomer(payload) {
                // AXIOS.put(this.$router.params.entitiesName, payload)
                AXIOS.put('customers', payload)
                    .then(() => {
                        this.getCustomers();
                        this.message = 'Book updated!';
                        this.showMessage = true;
                    })
                    .catch((error) => {
                        // eslint-disable-next-line
                        console.error(error);
                        this.getCustomers();
                    });
            },
            onResetUpdate(evt) {
                evt.preventDefault();
                this.$refs.editBookModal.hide();
                this.initForm();
                this.getCustomers(); // why?
            },
            removeCustomer(id) {
                // AXIOS.delete(`${this.$router.params.entitiesName}/${id}`)
                AXIOS.delete(`customers/${id}`)
                        .then(() => {
                        this.getCustomers();
                        this.message = 'Customer removed!';
                        this.showMessage = true;
                    })
                    .catch((error) => {
                        // eslint-disable-next-line
                        console.error(error);
                        this.getCustomers();
                    });
            },
            onDeleteCustomer(book) {
                this.removeCustomer(book.id);
            },
        },
        created() {
            this.getCustomers();
        },
    };
</script>