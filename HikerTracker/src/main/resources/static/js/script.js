window.addEventListener('load', function(e){
	console.log('script.js loaded');
	init();
});

function init(){
	document.hikersForm.findAll.addEventListener('click',function(event){
		event.preventDefault();
		getAllHikers();
	});

	document.createForm.addHike.addEventListener('click',function(event){
		event.preventDefault();
		let cf = document.createForm;
		let newHike = {
			hikerId: cf.hikerId.value,
			trailId: cf.trailId.value,
			distance: cf.distance.value,
			hikingDate: cf.hikeDate.value
		};
		postNewHike(newHike);
	});

	document.updateForm.updateHike.addEventListener('click', function(event){
		event.preventDefault();
		let uf = document.updateForm;
		let updateHike = {
			hikerId: uf.hikerId.value,
			trailId: uf.trailId.value,
			hikeId: uf.hikeId.value,
			distance: uf.distance.value,
			hikingDate: uf.hikeDate.value 
		};
		updateHikeActivity(updateHike);
	});
}

//hikings/{hikerId}/{trailId}/hiking
function postNewHike(newHike){
	let xhr = new XMLHttpRequest();
	xhr.open('POST', `api/hikings/${newHike.hikerId}/${newHike.trailId}/hiking`);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200 || xhr.status===201){
				let hike = JSON.parse(xhr.responseText);
				// getAllHikeActivities(newHike.hikerId);
				getAllHikeActivities(hike.hiker.id);
			}else{
				let create = document.getElementById('createData');
				create.textContent = 'Fail to add new hike activity!';
			}
		}
	};
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(newHike));
}

//hikings/{hikerId}/{trailId}/{hikingId}/hiking
function updateHikeActivity(updateHike){
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', `api/hikings/${updateHike.hikerId}/${updateHike.trailId}/${updateHike.hikeId}/hiking`);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200 || xhr.status===201){
				let hike = JSON.parse(xhr.responseText);
				getAllHikeActivities(hike.hiker.id);
			}else{
				let update = document.getElementById('updateData');
				update.textContent = 'Fail to update hike activity!';
			}
		}
	};
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(updateHike));
}


function getAllHikers(){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/hikers');
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let hikers = JSON.parse(xhr.responseText);
				displayHikers(hikers);
			}else{
				let hikersData = document.getElementById('hikersData');
				hikersData.textContent = 'No hikers found!';
			}
		}
	}
	xhr.send(null);
}

function displayHikers(hikers){
	let hikersData = document.getElementById('hikersData');
	hikersData.textContent = '';
	let table = document.createElement('table');
	let thead = document.createElement('thead');
	hikersData.appendChild(table);
	table.appendChild(thead);
	let tr = document.createElement('tr');
	thead.appendChild(tr);
	let th = document.createElement('th');
	th.textContent = 'hikerId';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Name';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Gender';
	tr.appendChild(th);

	let tbody = document.createElement('tbody');
	table.appendChild(tbody);
	for(let hiker of hikers){
		tr = document.createElement('tr');
		let td = document.createElement('td');
		td.textContent = hiker.id;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hiker.name;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hiker.gender;
		tr.appendChild(td);
		let button = document.createElement('button');
		button.textContent = 'All Hike Details';
		tr.appendChild(button);
		tbody.appendChild(tr);
		
		button.addEventListener('click',function(event){
			event.preventDefault();
			getAllHikeActivities(hiker.id);
		});
	}
}
//hikings/{hikerId}
function getAllHikeActivities(hikerId){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/hikings/'+hikerId);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let hikeItems = JSON.parse(xhr.responseText);
				displayHikeActivities(hikeItems);
			}else{
				let hikeData = document.getElementById('hikeData');
				hikeData.textContent = 'No hike found!';
			}
		}
	}
	xhr.send(null);
}

function displayHikeActivities(hikeItems){
	let hikeData = document.getElementById('hikeData');
	let thead = document.getElementById('hikeThead');
	while(thead!=null && thead.firstElementChild){
		thead.removeChild(thead.firstElementChild);
	}
	let tbody = document.getElementById('hikeTbody');
	while(tbody!=null && tbody.firstElementChild){
		tbody.removeChild(tbody.firstElementChild);
	}

	let tr = document.createElement('tr');
	thead.appendChild(tr);
	let th = document.createElement('th');
	th.textContent = 'hikeId';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Distance';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Hike Date';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'HikerId';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'TrailId';
	tr.appendChild(th);

	for(let hike of hikeItems){
		tr = document.createElement('tr');
		let td = document.createElement('td');
		td.textContent = hike.id;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.distance;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.hikingDate;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.hiker.id;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.trail.id;
		tr.appendChild(td);
		tbody.appendChild(tr);

		let button = document.createElement('button');
		button.textContent = 'Delete';
		tr.appendChild(button);
		button.addEventListener('click',function(event){
			event.preventDefault();
			console.log('deleteListener1');
			deleteHikeActivity(hike);
			console.log('deleteListener2');
		});


	}
}

function deleteHikeActivity(hike){
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', `api/hikings/${hike.hiker.id}/${hike.id}/hiking`);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200 || xhr.status===204){
				//let jhike = JSON.parse(xhr.responseText);
				getAllHikeActivities(hike.hiker.id);
			}else{
				let deleted = document.getElementById('deleteData');
				deleted.textContent = 'Fail to delete hike activity!';
			}
		}
	};
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(hike));
}
//todo
//retrieve all hikers and display in table
//click on a hiker to display details
//button to delete 
//button to update, load hikers into form inputs with button to put
//
