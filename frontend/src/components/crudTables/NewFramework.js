import { Button, CircularProgress } from '@material-ui/core';
import React from 'react';
import MUIDataTable from 'mui-datatables';
import {AXIOS} from '../http-common'

export default class NewFramework extends React.Component {
  state = {
    serverSideFilterList: [],
    filters: [[], []],
    isLoading: false,
    data: []
  };

  handleFilterSubmit = filterList => () => {
    console.log('Submitting filters: ', filterList);

    this.setState({ isLoading: true, filters: filterList });

    new Promise((resolve, reject) =>
        AXIOS.get(
            `/customers`,
            {
                params: {
                    name: 'q',
                }
            })
            .then(response => {
                resolve({
                    data: response.data.content,
                    page: response.data.pageNumber,
                    totalCount: response.data.totalElements,
                })
            })
            .catch((reason) => {
                this.showMessage(`Error refreshing ${this.props.entityName}s: ${reason}`)
                reject()
            })
    ).then(res => {
        this.setState({ isLoading: false, data: res.data, serverSideFilterList: filterList });
    })

    // // fake async request
    // this.xhrRequest(`/myApiServer?filters=${filterList}`, filterList).then(res => {
    //   this.setState({ isLoading: false, data: res.data, serverSideFilterList: filterList });
    // });
  };

  render() {
    const columns = [
      {
        name: 'Id',
        options: {
          filter: true,
        //   filterList: this.state.filters[0],
        },
      },
      {
        label: 'Name',
        name: 'Name',
        options: {
          filter: true,
        //   filterList: this.state.filters[1],
        },
      },
    ];

    const options = {
      filter: true,
      serverSideFilterList: this.state.serverSideFilterList,
      filterType: 'dropdown',
      responsive: 'scrollMaxHeight',
      serverSide: true,
      onFilterDialogOpen: () => {
        console.log('filter dialog opened');
      },
      onFilterDialogClose: () => {
        console.log('filter dialog closed');
      },
      onFilterChange: (column, filterList, type) => {
        if (type === 'chip') {
          console.log('updating filters via chip');
          this.handleFilterSubmit(filterList)();
        }
      },
      customFilterDialogFooter: filterList => {
        return (
          <div style={{ marginTop: '40px' }}>
            <Button variant="contained" onClick={this.handleFilterSubmit(filterList)}>Apply Filters*</Button>
            <p><em>*(Simulates selecting "Chicago" from "Location")</em></p>
          </div>
        );
      }
    };

    return (
      <React.Fragment>
        {this.state.isLoading && (
          <div style={{ position: 'absolute', top: '50%', left: '50%' }}>
            <CircularProgress />
          </div>
        )}
        <MUIDataTable title={'Customers'} data={this.state.data} columns={columns} options={options} />
      </React.Fragment>
    );
  }
}
